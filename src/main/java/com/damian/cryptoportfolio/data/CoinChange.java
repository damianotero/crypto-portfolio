package com.damian.cryptoportfolio.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinChange {

    @JsonProperty("percentage")
    private String percentage;
    @JsonProperty("absolute")
    private String absolute;


    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getAbsolute() {
        return absolute;
    }

    public void setAbsolute(String absolute) {
        this.absolute = absolute;
    }
}
