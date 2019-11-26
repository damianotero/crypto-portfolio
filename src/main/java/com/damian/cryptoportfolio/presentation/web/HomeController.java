package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.authentification.UserAuthentication;
import com.damian.cryptoportfolio.logic.services.CoinService;
import com.damian.cryptoportfolio.logic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthentication userAuthentication;


    @GetMapping("/")
    public String home (Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            model.addAttribute("user",true);
//        }else {
//            model.addAttribute("user",false);
//        }
        return "home";
    }

    @GetMapping("/about")
    public String about (Model model){
        //  GETTING USER LOGGED FOR HEADER  //
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            model.addAttribute("user",true);
//        }else {
//            model.addAttribute("user",false);
//        }
        ///////////////
        return "about";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout";// para redirigir a la pantalla de login
    }
//TODO  LOGOUT
    //todo login page






}
