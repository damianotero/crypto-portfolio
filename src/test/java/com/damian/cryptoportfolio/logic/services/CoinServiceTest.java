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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CoinServiceTest {

    public static final String NAME = "test name";
    public static final int ID = 123;

    @InjectMocks
    CoinService coinService;

    @Mock
    CoinRepository coinRepository;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCoins_ShouldReturnAListOfCoins() {
        List<Coin> coins = aListOfCoins();
        when(coinRepository.getCoins()).thenReturn(coins);

        List<Coin> createdList = coinService.getCoins();

        assertThat(createdList).isEqualTo(coins);
    }

    @Test
    public void getCoinsByUserWithValidUser_shouldReturnAListOfCoins() {
        User user = aUserWith(NAME);
        List<Coin> coins = aListOfCoins();
        when(coinRepository.getCoinsByUser(user)).thenReturn(coins);

        List<Coin> createdList = coinService.getCoinsByUser(user);

        assertThat(createdList).isEqualTo(coins);
    }



    @Test
    public void whenAddingANewCoin_coinIsCreated() {
        Coin coin = aCoinWith(NAME);

        coinRepository.addCoin(coin);
        verify(coinRepository).addCoin(coin);
    }

    @Test
    public void afterDeleteCoinById_ShouldNotFindCoin() {
        List<Coin> coins = aListOfCoins();
        coins.add(aCoinWith(ID));

        coinService.deleteCoin(ID);
        assertThat(coinService.getCoinById(ID)).isNull();

    }

    private User aUserWith(String name) {
        User user = new User();
        user.setName(name);
        user.setId(23423423);
        user.setPassword("3245234");
        return user;
    }

    private List<Coin> aListOfCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(aCoinWith("name1"));
        coins.add(aCoinWith("name2"));
        coins.add(aCoinWith("name3"));
        return coins;
    }

    private Coin aCoinWith(String name){
        Coin coin =new Coin();
        coin.setName(name);
        coin.setId(123123);
        return coin;
    }

    private Coin aCoinWith(int id){
        Coin coin =new Coin();
        coin.setName(NAME);
        coin.setId(id);
        return coin;
    }
}