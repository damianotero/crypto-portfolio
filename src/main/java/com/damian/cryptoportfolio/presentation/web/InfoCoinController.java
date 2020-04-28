package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.services.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/coins/info")
public class InfoCoinController {

    @Autowired
    private CoinService coinService;

    @RequestMapping(path = "/{name}",method = RequestMethod.GET, produces="application/json")
    public Coin read(@PathVariable String name, Model model){
        Coin coin = coinService.getCoinByName(name);
        model.addAttribute("coin", coin);
        return coin;
    }

}
