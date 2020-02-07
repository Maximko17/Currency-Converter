package com.testexercise.javajuniorproject.domain;

import java.util.Date;

public class History {

    private Integer id;
    private String startCurrency;
    private String endCurrency;
    private Double startAmount;
    private Double endAmount;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartCurrency() {
        return startCurrency;
    }

    public void setStartCurrency(String startCurrency) {
        this.startCurrency = startCurrency;
    }

    public String getEndCurrency() {
        return endCurrency;
    }

    public void setEndCurrency(String endCurrency) {
        this.endCurrency = endCurrency;
    }

    public Double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(Double startAmount) {
        this.startAmount = startAmount;
    }

    public Double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Double endAmount) {
        this.endAmount = endAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
