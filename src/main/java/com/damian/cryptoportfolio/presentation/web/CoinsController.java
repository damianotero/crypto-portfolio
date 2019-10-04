package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private CoinService coinService;

    @GetMapping("/list")
    public String listCoins(Model model){
        model.addAttribute("coinList", coinService.getCoins());
        return "coin_list";
    }

    @GetMapping("/add-coin")
    public String addCoinForm(Model model){
        model.addAttribute("coin", new Coin());
        return "add-coin";
    }

    @PostMapping("/add-coin")
    public String addCoinSubmit(@ModelAttribute Coin coin, BindingResult errors, Model model){
        coinService.addCoin(coin);
        model.addAttribute("coinList", coinService.getCoins());
        return "coin_list";

    }

   @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCoin(@RequestParam String id, Model model){
        coinService.deleteCoin(Integer.valueOf(id));
       model.addAttribute("coinList", coinService.getCoins());
        return "coin_list";
   }


}
