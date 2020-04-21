package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.CoinRepository;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CoinServiceTest {

    @InjectMocks
    CoinService coinService;

    @Mock
    CoinRepository coinRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin());
        coins.add(new Coin());

        when(coinRepository.getCoins()).thenReturn(coins);
    }

    @Test
    public void getCoinsByUser() {
        User user = new User();
        List<Coin> coinList = new ArrayList<>();
        coinList.add(new Coin());
        user.setName("test name");
        user.setId(23423423);
        user.setPassword("3245234");

        when(coinRepository.getCoinsByUser(user)).thenReturn(coinList);
    }

    @Test
    public void addCoin() {
        Coin coin = new Coin();

        coinRepository.addCoin(coin);
        verify(coinRepository).addCoin(coin);
    }

    @Test
    public void deleteCoin() {

        Coin coin = new Coin();
        coin.setId(123);

        coinRepository.deleteCoin(123);
        verify(coinRepository).deleteCoin(123);
    }
}