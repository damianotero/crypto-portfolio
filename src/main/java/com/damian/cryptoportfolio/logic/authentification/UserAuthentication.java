package com.damian.cryptoportfolio.logic.authentification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAuthentication {
    //


    public String getUserAuthenticated() {
        log.info("getUserAuthenticated(): Method called");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
             username = principal.toString();
        }
        return username;
    }
}



