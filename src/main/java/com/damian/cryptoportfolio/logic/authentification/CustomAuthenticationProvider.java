package com.damian.cryptoportfolio.logic.authentification;

import com.damian.cryptoportfolio.logic.models.User;
import com.damian.cryptoportfolio.logic.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        log.info("authenticate(): Method called");
        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();
        log.info("authenticate(): username: "+ username);
        List<User> userList = userService.getUsers();
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                log.info("authenticate(): User and password match");
                return new UsernamePasswordAuthenticationToken
                        (username, password, Collections.emptyList());
            }
        }

        throw new
                BadCredentialsException("External system authentication failed");

    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}