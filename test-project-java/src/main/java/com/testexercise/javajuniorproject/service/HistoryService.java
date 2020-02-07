package com.testexercise.javajuniorproject.service;

import com.testexercise.javajuniorproject.domain.History;

import java.util.List;

public interface HistoryService {

    List<History> findAll(String date, String startCurrency,String endCurrency);
    void insertHistoryRow(History emp);
}
