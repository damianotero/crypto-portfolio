package com.damian.cryptoportfolio.data.jsonproperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinPrice {

    @JsonProperty("price")
    private Price price;

    @JsonProperty("volume")
    private String volume;
    @JsonProperty("volumeQuote")
    private String volumeQuote;

}


//TODO UNIFICAR CLASES