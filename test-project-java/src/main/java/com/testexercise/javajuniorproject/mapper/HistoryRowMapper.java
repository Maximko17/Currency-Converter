package com.testexercise.javajuniorproject.mapper;

import com.testexercise.javajuniorproject.domain.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRowMapper implements RowMapper<History> {

    @Override
    public History mapRow(ResultSet resultSet, int i) throws SQLException {
        History history = new History();
        history.setStartCurrency(resultSet.getString("startCurrency"));
        history.setEndCurrency(resultSet.getString("endCurrency"));
        history.setStartAmount(resultSet.getDouble("startAmount"));
        history.setEndAmount(resultSet.getDouble("endAmount"));
        history.setDate(resultSet.getDate("date"));

        return history;
    }
}
