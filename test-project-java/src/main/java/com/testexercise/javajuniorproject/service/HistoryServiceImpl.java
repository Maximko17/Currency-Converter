package com.testexercise.javajuniorproject.service;

import com.testexercise.javajuniorproject.domain.History;
import com.testexercise.javajuniorproject.mapper.HistoryRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HistoryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<History> findAll(String date, String startCurrency, String endCurrency) {

        if (date.isEmpty()) {
            if (startCurrency.isEmpty() && !endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history WHERE endCurrency = '"+ endCurrency +"' ORDER BY id DESC ", new HistoryRowMapper());
            }else if (!startCurrency.isEmpty() && endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history WHERE startCurrency = '"+ startCurrency +"' ORDER BY id DESC", new HistoryRowMapper());
            }else if (startCurrency.isEmpty() && endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history ORDER BY id DESC", new HistoryRowMapper());
            }else {
                return jdbcTemplate.query("SELECT * FROM history WHERE startCurrency = '"+ startCurrency +"' AND endCurrency = '"+ endCurrency +"' ORDER BY id DESC", new HistoryRowMapper());
            }
        }else{
            if (startCurrency.isEmpty() && !endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history WHERE date = '"+ date +"' AND endCurrency = '"+ endCurrency +"' ORDER BY id DESC", new HistoryRowMapper());
            }else if (!startCurrency.isEmpty() && endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history WHERE date = '"+ date +"' AND startCurrency = '"+ startCurrency +"' ORDER BY id DESC", new HistoryRowMapper());
            }else if (startCurrency.isEmpty() && endCurrency.isEmpty()){
                return jdbcTemplate.query("SELECT * FROM history WHERE date = '"+ date +"' ORDER BY id DESC", new HistoryRowMapper());
            }else{
                return jdbcTemplate.query("SELECT * FROM history WHERE date = '"+ date +"' AND startCurrency = '"+ startCurrency +"' AND endCurrency = '"+ endCurrency +"' ORDER BY id DESC", new HistoryRowMapper());
            }
        }
    }

    @Override
    public void insertHistoryRow(History hs) {
        final String sql = "INSERT INTO history(startCurrency, endCurrency,startAmount, endAmount)" +
                " values(:startCurrency,:endCurrency,:startAmount,:endAmount)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("startCurrency", hs.getStartCurrency())
                .addValue("endCurrency", hs.getEndCurrency())
                .addValue("startAmount", hs.getStartAmount())
                .addValue("endAmount", hs.getEndAmount());
        jdbcTemplate.update(sql, param, holder);
    }
}
