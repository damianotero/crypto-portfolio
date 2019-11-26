package com.damian.cryptoportfolio.logic.authentification;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.
                ignoring().antMatchers("/")
                .and().ignoring().antMatchers("/about")
                .and().ignoring().antMatchers("/users/addUser/**")
                .and().ignoring().antMatchers("/css/**")
        ;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().and()
                .formLogin()
                .failureForwardUrl("/login_failed.html")




                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

        ;

    }

}
