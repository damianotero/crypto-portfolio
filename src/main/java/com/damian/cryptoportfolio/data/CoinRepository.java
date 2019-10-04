package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.CoinPriceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CoinRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Coin> getCoins(){
        List<Coin> listCoins = jdbcTemplate.query("SELECT * FROM coins", new CoinRowMapper());
        for (Coin c: listCoins) {
            c.setPrice(getPrice(c.getToken()));
        }
        return listCoins;
    }

    public void addCoin(Coin coin){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", coin.getName())
                .addValue("token", coin.getToken())
                .addValue("amount", coin.getAmount());
        jdbcTemplate.update("INSERT INTO coins (c_name, c_token, c_amount) " +
                "values ( :name , :token , :amount );",namedParameters);
    }

    public void deleteCoin(int id){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update("DELETE FROM coins WHERE c_id = :id ; ",namedParameters);
    }

    public double getPrice (String token){
        
        RestTemplate restTemplate = new RestTemplate();
        CoinPriceResult coinPriceResult = restTemplate.getForObject("https://api.cryptowat.ch/markets/bitfinex/"+token+"usd/price", CoinPriceResult.class);
        
        return Double.valueOf(coinPriceResult.getResult().getPrice());
    }
}
