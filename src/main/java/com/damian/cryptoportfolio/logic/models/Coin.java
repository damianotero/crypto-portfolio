package com.damian.cryptoportfolio.logic.models;

import java.util.HashMap;

public class Coin {

    private int id;
    private String name;
    private String token;
    private double amount;
    private double price;
    private int userId;
    private Double percentage;


    private HashMap<String, String> tokens =new HashMap<>();

    public HashMap<String,String> setMap() {
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
        return tokens;
    }

    public HashMap<String, String> getTokens() {
        return tokens;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    public String setNameToken() {
//        this.name = tokens.get(token);
//    }

//    public static void main(String[] args) {
//        Coin coin = new Coin();
//        coin.setToken("BTC");
//        coin.setNameToken();
//        System.out.println(tokens.get(coin.token));
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
