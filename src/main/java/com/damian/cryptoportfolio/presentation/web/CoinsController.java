package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.authentification.UserAuthentication;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.CoinService;
import com.damian.cryptoportfolio.logic.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthentication userAuthentication;


    @GetMapping("/list")
    public String listCoins(Model model) {
        log.info("listCoins(): Method from controller called");
        User user = userService.findUserByName(userAuthentication.getUserAuthenticated());

        if (user != null) {
            log.info("listCoins(): User: " + user.getName() + " authenticated");
            List<Coin> coinList = coinService.getCoinsByUser(user);
            model.addAttribute("userName", user.getName());
            model.addAttribute("coinList", coinList);
            log.info("listCoins(): Retrieving List of coins");
            return "coin_list";
        } else {
            log.info("listCoins(): Authentication failed, redirecting");
            return "redirect:/users/login";
        }
    }

    @GetMapping("/add-coin")
    public String addCoinForm(Model model) {
        log.info("addCoinForm(): Method from controller called");

        User user = userService.findUserByName(userAuthentication.getUserAuthenticated());
        if (user != null) {
            log.info("addCoinForm(): User: " + user.getName() + " authenticated, redirecting to add-coin");
            model.addAttribute("coin", new Coin());
            return "add-coin";
        } else {
            log.info("addCoinForm(): Authentication failed, redirecting");
            return "redirect:/users/login";
        }
    }

    @PostMapping("/add-coin")
    public String addCoinSubmit(@ModelAttribute Coin coin, BindingResult errors, Model model) {
        log.info("addCoinSubmit(): Post Method controller called.");
        User user = userService.findUserByName(userAuthentication.getUserAuthenticated());
        coin.setUserId(user.getId());
        coinService.addCoin(coin);
        log.info("addCoinSubmit(): Adding coin: " + coin.getName() + " to the User: " + user.getName());
        model.addAttribute("coinList", coinService.getCoinsByUser(user));
        model.addAttribute("userName", user.getName());

        return "coin_list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCoin(@RequestParam String id, Model model) {
        log.info("deleteCoin(): Method from controller called");
        User user = userService.findUserByName(userAuthentication.getUserAuthenticated());
        coinService.deleteCoin(Integer.valueOf(id));
        log.info("deleteCoin(): Deleting coin with id:" + id + " from User: " + user.getName());
        model.addAttribute("coinList", coinService.getCoinsByUser(user));
        return "coin_list";
    }
}