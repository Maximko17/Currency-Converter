package com.testexercise.javajuniorproject.dto;

public class ConverterDTO {

    private String startCurrencyId;
    private String endCurrencyId;
    private Double startAmount;
    private Double endAmount;

    public String getStartCurrencyId() {
        return startCurrencyId;
    }

    public void setStartCurrencyId(String startCurrencyId) {
        this.startCurrencyId = startCurrencyId;
    }

    public String getEndCurrencyId() {
        return endCurrencyId;
    }

    public void setEndCurrencyId(String endCurrencyId) {
        this.endCurrencyId = endCurrencyId;
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
}
