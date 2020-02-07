package com.testexercise.javajuniorproject.service;

import com.testexercise.javajuniorproject.domain.Currency;
import com.testexercise.javajuniorproject.domain.History;
import com.testexercise.javajuniorproject.dto.ConverterDTO;
import com.testexercise.javajuniorproject.mapper.CurrencyRowMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final HistoryService historyService;

    public ConverterServiceImpl(NamedParameterJdbcTemplate jdbcTemplate, HistoryService historyService) {
        this.jdbcTemplate = jdbcTemplate;
        this.historyService = historyService;
    }

    @Override
    public List<Currency> getAllCurrency(){
        String selectQuery = "SELECT * FROM currency";

        return jdbcTemplate.query(selectQuery,new CurrencyRowMapper());
    }

    @Override
    public Double convert(ConverterDTO converterDTO) {
        String startCurrencyQuery = "SELECT * FROM currency WHERE id = :id";
        SqlParameterSource startParam = new MapSqlParameterSource()
                .addValue("id", converterDTO.getStartCurrencyId());
        Currency startCurrency = jdbcTemplate.queryForObject(
                startCurrencyQuery,startParam, new CurrencyRowMapper());

        String endCurrencyQuery = "SELECT * FROM currency WHERE id = :id";
        SqlParameterSource endParam = new MapSqlParameterSource()
                .addValue("id", converterDTO.getEndCurrencyId());
        Currency endCurrency = jdbcTemplate.queryForObject(endCurrencyQuery,endParam, new CurrencyRowMapper());

        if (startCurrency.getNominal() > 1){
            startCurrency.setValue(startCurrency.getValue() / startCurrency.getNominal());
        }

        if (endCurrency.getNominal() > 1){
            endCurrency.setValue(endCurrency.getValue() / endCurrency.getNominal());
        }

        double endAmount = startCurrency.getValue() * converterDTO.getStartAmount() / endCurrency.getValue();
        DecimalFormat df = new DecimalFormat("#.####");
        String dx=df.format(endAmount).replace(",",".");
        endAmount = Double.valueOf(dx);

        History history = new History();
        history.setStartCurrency(startCurrency.getCharCode());
        history.setEndCurrency(endCurrency.getCharCode());
        history.setStartAmount(converterDTO.getStartAmount());
        history.setEndAmount(endAmount);

        historyService.insertHistoryRow(history);

        return endAmount;
    }

    @Override
    public void updateCurrency() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String cbrResourceUrl = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormat.format(new Date());
        ResponseEntity<String> response = restTemplate.getForEntity(cbrResourceUrl, String.class);

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        InputSource inputSource;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            inputSource = new InputSource(new StringReader(response.getBody()));
            saxParser.parse(inputSource, handler);
            List<Currency> currencyList = handler.getCurrencyList();
            for (Currency currency : currencyList) {

                String countQuery = "SELECT count(*) FROM currency WHERE id = :id";
                Map<String, String> param = new HashMap<>();
                param.put("id", currency.getId());
                int count = jdbcTemplate.queryForObject(countQuery, param, Integer.class);

                String query;
                if (count > 0) {
                    query = "UPDATE currency SET name=:name,charCode=:charCode,nominal=:nominal,value=:value WHERE id = :id";
                } else {
                    query = "INSERT INTO currency (id,name,charCode,nominal,value) VALUES (:id, :name, :charCode, :nominal, :value)";
                }

                SqlParameterSource params = new MapSqlParameterSource()
                        .addValue("id", currency.getId())
                        .addValue("name", currency.getName())
                        .addValue("charCode", currency.getCharCode())
                        .addValue("nominal", currency.getNominal())
                        .addValue("value", currency.getValue());
                jdbcTemplate.update(query, params);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
