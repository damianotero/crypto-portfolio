package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUserGet(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userName", "Guess");

        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUserSubmmit(@ModelAttribute User user, BindingResult errors, Model model) {
        if (!userService.validateUser(user)) {
            userService.addUser(user);

            return "register_ok";
        } else {
            return "register_failed";
        }
    }
}
