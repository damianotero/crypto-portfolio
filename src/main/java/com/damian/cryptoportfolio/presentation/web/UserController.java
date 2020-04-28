package com.damian.cryptoportfolio.presentation.web;

import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUserGet(Model model) {
        log.info("addUserGet(): Controller Method called");
        model.addAttribute("user", new User());
        model.addAttribute("userName", "Guess");

        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUserSubmmit(@ModelAttribute User user, BindingResult errors, Model model) {
        log.info("addUserSubmit(): Controller Method called");
        if (!userService.userExists(user)) {
            userService.addUser(user);
            log.info("addUserSubmit(): User: " + user.getName()+ " is registered Ok");
            return "register_ok";
        } else {
            log.info("addUserSubmit(): User: " + user.getName() + " already exists.");
            return "register_failed";
        }
    }

    //TODO  When tries to register user that already exists, it crashes. Create an exception.
}
