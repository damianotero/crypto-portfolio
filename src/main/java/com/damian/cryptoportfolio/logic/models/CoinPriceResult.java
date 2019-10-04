package com.damian.cryptoportfolio.logic.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinPriceResult {

    @JsonProperty("result")
    private CoinPrice result;

    public CoinPrice getResult() {
        return result;
    }

    public void setResult(CoinPrice result) {
        this.result = result;
    }
}
