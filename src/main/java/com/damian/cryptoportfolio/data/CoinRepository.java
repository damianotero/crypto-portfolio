package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.data.jsonproperties.CoinPriceOnly;
import com.damian.cryptoportfolio.data.jsonproperties.CoinPriceResult;
import com.damian.cryptoportfolio.logic.authentification.UserAuthentication;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.UserService;
import com.damian.cryptoportfolio.presentation.web.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Slf4j
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


    public List<Coin> getCoins() {
        log.info("getCoins(): Method called");
        List<Coin> listCoins = jdbcTemplate.query("SELECT * FROM coins", new CoinRowMapper());
        log.info("getCoins(): Retrieving the list of coins.");
        for (Coin coin : listCoins) {
            coin.setPrice(getPrice(coin.getToken()));
            coin.setPercentage(getPercentage(coin.getToken()));
            log.info("getCoins(): Getting price and percentage from the API and setting in the Coin object");
        }
        return listCoins;
    }

    public List<Coin> getCoinsByUser(User user) {
        log.info("getCoinsByUser(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userName", user.getName());
        List<Coin> listCoins = jdbcTemplate
                .query("select coins.c_id, coins.c_name, coins.c_token, coins.c_amount, coins.c_u_id  from coins inner join users on coins.c_u_id = users.u_id where u_name = :userName ;", namedParameters, new CoinRowMapper());
        log.info("getCoinsByUser(" + user.getName() + "):  Query to DB: c_name, c_token, c_amount, c_u_id, from user:" + user.getName());
        for (Coin coin : listCoins) {
            coin.setPrice(getPrice(coin.getToken()));
            coin.setPercentage(getPercentage(coin.getToken()));
        }
        log.info("getCoinsByUser(): Getting price and percentage from the API and setting in the Coin object");
        return listCoins;
    }

    public void addCoin(Coin coin) {
        log.info("addCoin(): Method called");
        HashMap<String, String> tokensMap = coin.setMap();
        coin.setName(tokensMap.get(coin.getToken()));
        log.info("addCoin(): Setting name of the coin from map of coin names and tokens.");
        User user = userService.findUserByName(userAuthentication.getUserAuthenticated());
        log.info("addCoin(): Getting user authenticated.  -> getUserByName(userAuthentication.getUserAuthenticated()");
        List<Coin> coinList = getCoinsByUser(user);
        log.info("addCoin(): Getting coin list by user and check if already exists");
        for (Coin userCoins : coinList) {
            if (userCoins.getName().equalsIgnoreCase(coin.getName())) {
                double newAmount = coin.getAmount() + userCoins.getAmount();
                SqlParameterSource namedParameters = new MapSqlParameterSource()
                        .addValue("amount", newAmount)
                        .addValue("userId", user.getId())
                        .addValue("token", coin.getToken());
                jdbcTemplate.update("UPDATE coins SET c_amount= :amount where (c_u_id=  :userId) and (c_token= :token);", namedParameters);
                log.info("addCoin(): The user:" + user.getName() + " already has " + coin.getName() + " the amount is updated with " + coin.getAmount());
                return;
            }

        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", coin.getName())
                .addValue("token", coin.getToken())
                .addValue("amount", coin.getAmount())
                .addValue("userId", user.getId());
        jdbcTemplate.update("INSERT INTO coins (c_name, c_token, c_amount, c_u_id) " +
                "values ( :name , :token , :amount , :userId );", namedParameters);
        log.info("addCoin(): Adding " + coin.getAmount() + " " + coin.getName() + " to the user: " + user.getName());
    }

    public void deleteCoin(int id) {
        log.info("deleteCoin(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update("DELETE FROM coins WHERE c_id = :id ; ", namedParameters);
        log.info("deleteCoin(): Deleting coin by id");
    }

    public double getPrice(String token) {
        log.info("getPrice(" + token + "): Method called");
        RestTemplate restTemplate = new RestTemplate();
        CoinPriceOnly priceOnly = restTemplate.getForObject("https://api.cryptowat.ch/markets/bitfinex/" + token + "usd/price", CoinPriceOnly.class);
        log.info("getPrice(" + token + "): Get the price from API to a RestTemplate and return the value.");
        return Double.valueOf(priceOnly.getResult().getPrice());
    }

    public double getPercentage(String token) {
        log.info("getPercentage("+token+"): Method called");
        RestTemplate restTemplate = new RestTemplate();
        CoinPriceResult coinPriceResult = restTemplate.getForObject("https://api.cryptowat.ch/markets/bitfinex/" + token + "usd/summary", CoinPriceResult.class);
        log.info("getPrice(" + token + "): Get the percentage from API to a RestTemplate and return the value.");
        double percentage = Double.valueOf(coinPriceResult.getResult().getPrice().getChange().getPercentage());
        return percentage * 100;

    }
}
