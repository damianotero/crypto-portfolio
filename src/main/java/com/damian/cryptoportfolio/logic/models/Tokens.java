package com.damian.cryptoportfolio.logic.models;

import java.util.HashMap;


public class Tokens {

    private HashMap<String, String> tokens =new HashMap<>();



    public Tokens() {
        tokens.put("BTC", "Bitcoin");
        tokens.put("ETH", "Etherum");
        tokens.put("XRP", "Ripple");
        tokens.put("BCH", "Bitcoin Cash");
        tokens.put("USDT", "Tether");
        tokens.put("LTC", "Litecoin");
        tokens.put("EOS", "EOS");
        tokens.put("BSV", "Bitcoin SV");
        tokens.put("XLM", "Stellar");
        tokens.put("TRX", "TRON");
        tokens.put("XMR", "Monero");

    }


    public HashMap<String, String> getTokens() {
        return tokens;
    }

    public void setTokens(HashMap<String, String> tokens) {
        this.tokens = tokens;
    }
}
