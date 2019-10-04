package com.damian.cryptoportfolio.logic.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinPrice {

    @JsonProperty("price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
