package com.damian.cryptoportfolio.data.jsonproperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinChange {

    @JsonProperty("percentage")
    private String percentage;
    @JsonProperty("absolute")
    private String absolute;

}
