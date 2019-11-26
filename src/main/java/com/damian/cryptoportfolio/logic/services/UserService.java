package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.UserRepository;
import com.damian.cryptoportfolio.logic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public boolean validateUser(User requestedUser) {
        return userRepository.validateUser(requestedUser);

    }


    public User getUserByName (String name){
        return userRepository.getUserByName(name);
    }
}