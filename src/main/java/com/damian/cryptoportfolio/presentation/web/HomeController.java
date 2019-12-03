package com.damian.cryptoportfolio.presentation.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

//    @Autowired
//    private CoinService coinService;

//    @Autowired
//    private UserController userController;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserAuthentication userAuthentication;


    @GetMapping("/")
    public String home(){

        return "home";
    }


    @GetMapping("/about")
    public String about(){

        return "about";
    }

    @GetMapping("/login")
    public String login(){

        return "user_login";
    }

    @GetMapping("/login_failed")
    public String loginFailed(){

        return "login_failed";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/logout";
    }






}
