package com.damian.cryptoportfolio.logic.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Coin {

    private int id;
    private String name;
    private String token;
    private double amount;
    private double price;
    private int userId;
    private Double percentage;
    private HashMap<String, String> tokens = new HashMap<>() {{
        put("BTC", "Bitcoin");
        put("ETH", "Etherum");
        put("XRP", "Ripple");
        put("BCH", "Bitcoin Cash");
        put("USDT", "Tether");
        put("LTC", "Litecoin");
        put("EOS", "EOS");
        put("BSV", "Bitcoin SV");
        put("XLM", "Stellar");
        put("TRX", "TRON");
        put("XMR", "Monero");
    }};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return name.equals(coin.name);
    }
}
