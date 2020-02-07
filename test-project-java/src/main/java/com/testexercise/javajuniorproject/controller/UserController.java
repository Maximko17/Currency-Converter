package com.testexercise.javajuniorproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/basicauth")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
