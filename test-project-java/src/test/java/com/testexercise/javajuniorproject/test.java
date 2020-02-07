package com.testexercise.javajuniorproject;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    @Test
    void name() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("date: " + dateFormat.format( new Date() ) );
    }
}
