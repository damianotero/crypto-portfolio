package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.CoinRepository;
import com.damian.cryptoportfolio.logic.models.Coin;
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

    public void addCoin(Coin coin) {
        coinRepository.addCoin(coin);
    }

    public void deleteCoin(int id){
        coinRepository.deleteCoin(id);
    }
}
