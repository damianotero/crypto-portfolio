package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinPrice {

    @JsonProperty("price")
    private Price price;

    @JsonProperty("volume")
    private String volume;
    @JsonProperty("volumeQuote")
    private String volumeQuote;


    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumeQuote() {
        return volumeQuote;
    }

    public void setVolumeQuote(String volumeQuote) {
        this.volumeQuote = volumeQuote;
    }
}
