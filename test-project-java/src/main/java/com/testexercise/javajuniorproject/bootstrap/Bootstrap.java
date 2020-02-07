package com.testexercise.javajuniorproject.bootstrap;

import com.testexercise.javajuniorproject.service.ConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ConverterService converterService;

    public Bootstrap(ConverterService converterService) {
        this.converterService = converterService;
    }

    @Override
    public void run(String... args) throws Exception {
        converterService.updateCurrency();
    }
}
