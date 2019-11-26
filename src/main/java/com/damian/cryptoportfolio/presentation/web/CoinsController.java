package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.authentification.UserAuthentication;
import com.damian.cryptoportfolio.logic.models.Coin;
import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.CoinService;
import com.damian.cryptoportfolio.logic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        User user = userService.getUserByName(userAuthentication.getUserAuthenticated());

//        //  GETTING USER LOGGED FOR HEADER  //
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            model.addAttribute("user",true);
//        }else {
//            model.addAttribute("user",false);
//        }
//                 ///////////////
        if (user != null) {
            List<Coin> coinList = coinService.getCoinsByUser(user);
            model.addAttribute("userName", user.getName());
            model.addAttribute("coinList", coinList);

            return "coin_list";
        } else {
            return "redirect:/users/login";
        }
    }

    @GetMapping("/add-coin")
    public String addCoinForm(Model model) {
//        //  GETTING USER LOGGED FOR HEADER  //
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            model.addAttribute("user",true);
//        }else {
//            model.addAttribute("user",false);
//        }
//        ///////////////
        User user = userService.getUserByName(userAuthentication.getUserAuthenticated());
        if (user != null) {
            model.addAttribute("coin", new Coin());
            return "add-coin";
        } else {
            return "redirect:/users/login";
        }
    }

    @PostMapping("/add-coin")
    public String addCoinSubmit(@ModelAttribute Coin coin, BindingResult errors, Model model) {
        User user = userService.getUserByName(userAuthentication.getUserAuthenticated());
        coin.setUserId(user.getId());
        coinService.addCoin(coin);
        model.addAttribute("coinList", coinService.getCoinsByUser(user));
        model.addAttribute("userName", user.getName());

        return "coin_list";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCoin(@RequestParam String id, Model model) {
        User user = userService.getUserByName(userAuthentication.getUserAuthenticated());
        coinService.deleteCoin(Integer.valueOf(id));
        model.addAttribute("coinList", coinService.getCoinsByUser(user));
        return "coin_list";
    }


}
