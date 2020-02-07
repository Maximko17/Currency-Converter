package com.testexercise.javajuniorproject.controller;

import com.testexercise.javajuniorproject.domain.History;
import com.testexercise.javajuniorproject.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<History>> getHistory(@RequestParam("date") String date, @RequestParam("startCurrency") String startCurrency,
                                                    @RequestParam("endCurrency")String endCurrency){
        return new ResponseEntity<>(historyService.findAll(date,startCurrency,endCurrency), HttpStatus.OK);
    }
}
