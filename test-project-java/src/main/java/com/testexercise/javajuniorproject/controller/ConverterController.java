package com.testexercise.javajuniorproject.controller;

import com.testexercise.javajuniorproject.domain.Currency;
import com.testexercise.javajuniorproject.dto.ConverterDTO;
import com.testexercise.javajuniorproject.service.ConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/converter/currency")
    public ResponseEntity<List<Currency>> updateCurrency() {
        return new ResponseEntity<>(converterService.getAllCurrency(), HttpStatus.OK);
    }

    @PostMapping("/converter/convert")
    public ResponseEntity<?> convert(@RequestBody ConverterDTO converterDTO) {
        if (converterDTO.getStartAmount() ==null || converterDTO.getStartCurrencyId() == null || converterDTO.getEndCurrencyId() == null ){
            return new ResponseEntity<>("Проверьте правильность введенных данных", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(converterService.convert(converterDTO), HttpStatus.OK);
    }
}
