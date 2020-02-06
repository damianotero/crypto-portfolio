package com.damian.cryptoportfolio.logic.services;

import com.damian.cryptoportfolio.data.UserRepository;
import com.damian.cryptoportfolio.logic.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addUser() {

        User user = new User();
        user.setName("test name");
        user.setId(23423423);
        user.setPassword("3245234");

        userService.addUser(user);
        verify(userRepository).addUser(user);

    }

    @Test
    public void getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        when(userRepository.getUsers()).thenReturn(userList);


    }

    @Test
    public void validateUser() {
        User user = new User();
        user.setName("test name");
        user.setId(23423423);
        user.setPassword("3245234");

        userService.validateUser(user);
        verify(userRepository).validateUser(user);
    }

    @Test
    public void getUserByName() {
        User user = new User();
        user.setName("test name");
        user.setId(23423423);
        user.setPassword("3245234");
        when(userRepository.getUserByName(anyString())).thenReturn(user);

    }
}