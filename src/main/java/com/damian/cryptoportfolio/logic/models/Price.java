package com.damian.cryptoportfolio.logic.models;

import com.damian.cryptoportfolio.data.jsonproperties.CoinChange;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

    @JsonProperty("last")
    private String last;
    @JsonProperty("high")
    private String high;
    @JsonProperty("low")
    private String low;

    @JsonProperty("change")
    private CoinChange change;


    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public CoinChange getChange() {
        return change;
    }

    public void setChange(CoinChange change) {
        this.change = change;
    }
}
