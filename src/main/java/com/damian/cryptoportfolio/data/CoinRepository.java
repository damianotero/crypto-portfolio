package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.authentification.UserAuthentication;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.UserService;
import com.damian.cryptoportfolio.presentation.web.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Repository
public class CoinRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthentication userAuthentication;





    public List<Coin> getCoins(){
        List<Coin> listCoins = jdbcTemplate.query("SELECT * FROM coins", new CoinRowMapper());
        for (Coin c: listCoins) {
            c.setPrice(getPrice(c.getToken()));
            c.setPercentage(getPercentage(c.getToken()));
        }
        return listCoins;
    }

    public List<Coin> getCoinsByUser(User user){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userName", user.getName());
        List<Coin> listCoins = jdbcTemplate
                .query("select coins.c_id, coins.c_name, coins.c_token, coins.c_amount, coins.c_u_id  from coins inner join users on coins.c_u_id = users.u_id where u_name = :userName ;", namedParameters, new CoinRowMapper());
        for (Coin c: listCoins) {
            c.setPrice(getPrice(c.getToken()));
            c.setPercentage(getPercentage(c.getToken()));


        }
        return listCoins;
    }

    public void addCoin(Coin coin){
//        Tokens tokens = new Tokens();
        HashMap<String,String > tokensMap = coin.setMap();
        coin.setName(tokensMap.get(coin.getToken()));
        User user = userService.getUserByName(userAuthentication.getUserAuthenticated());
        List<Coin> coinList = getCoinsByUser(user);
        for (Coin userCoins: coinList) {
            if (userCoins.getName().equalsIgnoreCase(coin.getName())){
                double newAmount = coin.getAmount()+userCoins.getAmount();
                SqlParameterSource namedParameters = new MapSqlParameterSource()
                        .addValue("amount", newAmount)
                        .addValue("userId", user.getId())
                        .addValue("token", coin.getToken())
                        ;
                jdbcTemplate.update("UPDATE coins SET c_amount= :amount where (c_u_id=  :userId) and (c_token= :token);",namedParameters);
                return;
            }

        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", coin.getName())
                .addValue("token", coin.getToken())
                .addValue("amount", coin.getAmount())
                .addValue("userId", user.getId());
        jdbcTemplate.update("INSERT INTO coins (c_name, c_token, c_amount, c_u_id) " +
                "values ( :name , :token , :amount , :userId );",namedParameters);
    }

    public void deleteCoin(int id){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update("DELETE FROM coins WHERE c_id = :id ; ",namedParameters);
    }

    public double getPrice (String token){
        
        RestTemplate restTemplate = new RestTemplate();
        CoinPriceOnly priceOnly = restTemplate.getForObject("https://api.cryptowat.ch/markets/bitfinex/"+token+"usd/price", CoinPriceOnly.class);

        return Double.valueOf(priceOnly.getResult().getPrice());
    }

    public double getPercentage (String token){

        RestTemplate restTemplate = new RestTemplate();
        CoinPriceResult coinPriceResult = restTemplate.getForObject("https://api.cryptowat.ch/markets/bitfinex/"+token+"usd/summary", CoinPriceResult.class);

        double percentage = Double.valueOf(coinPriceResult.getResult().getPrice().getChange().getPercentage());
        return percentage*100;

    }
}
