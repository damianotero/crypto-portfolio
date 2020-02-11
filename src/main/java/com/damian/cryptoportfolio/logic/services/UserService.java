package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.UserRepository;
import com.damian.cryptoportfolio.logic.exceptions.UserAlreadyExistsException;
import com.damian.cryptoportfolio.logic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        try {
            return userRepository.addUser(user);
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException("User Already Exists!!!", e);
        }
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public boolean userExists(User requestedUser) {
        return userRepository.userExists(requestedUser);
    }

    public User findUserByName(String name){
        try {
           return userRepository.findUserByName(name);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}