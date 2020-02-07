package com.testexercise.javajuniorproject.service;

import com.testexercise.javajuniorproject.domain.Currency;
import com.testexercise.javajuniorproject.dto.ConverterDTO;

import java.util.List;

public interface ConverterService {

    void updateCurrency();

    List<Currency> getAllCurrency();

    Double convert(ConverterDTO converterDTO);
}
