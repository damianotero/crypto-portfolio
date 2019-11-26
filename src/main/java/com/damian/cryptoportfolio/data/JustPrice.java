package com.damian.cryptoportfolio.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JustPrice {

    @JsonProperty("price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
