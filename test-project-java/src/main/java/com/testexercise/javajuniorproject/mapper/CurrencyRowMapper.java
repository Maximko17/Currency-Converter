package com.testexercise.javajuniorproject.mapper;

import com.testexercise.javajuniorproject.domain.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRowMapper implements RowMapper<Currency> {

    @Override
    public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
        Currency currency = new Currency();
        currency.setId(resultSet.getString("id"));
        currency.setName(resultSet.getString("name"));
        currency.setCharCode(resultSet.getString("charCode"));
        currency.setNominal(resultSet.getInt("nominal"));
        currency.setValue(resultSet.getDouble("value"));

        return currency;
    }
}
