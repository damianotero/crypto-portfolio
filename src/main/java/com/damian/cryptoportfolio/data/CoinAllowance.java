package com.damian.cryptoportfolio.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinAllowance {

    @JsonProperty("cost")
    private String cost;
    @JsonProperty("remaining")
    private String remaining;
    @JsonProperty("upgrade")
    private String upgrade;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }
}
