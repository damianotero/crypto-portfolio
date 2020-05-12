package com.damian.cryptoportfolio.data.jsonproperties;

import com.damian.cryptoportfolio.data.jsonproperties.CoinChange;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {

    @JsonProperty("last")
    private String last;
    @JsonProperty("high")
    private String high;
    @JsonProperty("low")
    private String low;

    @JsonProperty("change")
    private CoinChange change;


}
