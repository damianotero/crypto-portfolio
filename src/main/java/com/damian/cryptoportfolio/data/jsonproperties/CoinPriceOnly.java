package com.damian.cryptoportfolio.data.jsonproperties;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinPriceOnly {

    @JsonProperty("result")
    private JustPrice result;

    public JustPrice getResult() {
        return result;
    }

    public void setResult(JustPrice result) {
        this.result = result;
    }
}
