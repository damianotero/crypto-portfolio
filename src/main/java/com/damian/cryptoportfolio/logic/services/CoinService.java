package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.CoinRepository;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public List<Coin> getCoins() {
        return coinRepository.getCoins();
    }

    public List<Coin> getCoinsByUser(User user) {
        return coinRepository.getCoinsByUser(user);
    }

    public void addCoin(Coin coin) {
        coinRepository.addCoin(coin);
    }

    public void deleteCoin(int id) {
        coinRepository.deleteCoin(id);
    }

    public Coin getCoinByName(String name){
        Coin coin = new Coin();
        coin.setName(name);
        List<Coin> coinList = getCoins();
        for (Coin listedCoin : coinList) {
            if (listedCoin.equals(coin)){
                return listedCoin;
            }
        }
        return null;
    }

    public Coin getCoinById(int id) {
        Coin coin = new Coin();
        coin.setId(id);
        List<Coin> coinList = getCoins();
        for (Coin listedCoin : coinList) {
            if (listedCoin.getId()==id){
                return listedCoin;
            }
        }
        return null;
    }
}
