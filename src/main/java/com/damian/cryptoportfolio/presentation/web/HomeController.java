package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CoinService coinService;

    @GetMapping("/")
    public String home (Model model){
        model.addAttribute("coinList", coinService.getCoins());
        return "home";
    }

}
