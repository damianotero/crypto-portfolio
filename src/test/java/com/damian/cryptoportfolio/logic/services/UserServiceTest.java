package com.damian.cryptoportfolio.logic.services;


import com.damian.cryptoportfolio.data.UserRepository;
import com.damian.cryptoportfolio.logic.exceptions.UserAlreadyExistsException;
import com.damian.cryptoportfolio.logic.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    public static final String NAME = "test name";
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenAddingANewUser_userIsCreated() {
        User user = aUserWith(NAME);
        when(userRepository.addUser(user)).thenReturn(user);

        User createdUser = userService.addUser(user);

        assertThat(createdUser).isEqualTo(user);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void whenAddingAUserThatAlreadyExists_throwsUserAlreadyExistsException() {
        User user = aUserWith(NAME);
        when(userRepository.addUser(user)).thenThrow(DataIntegrityViolationException.class);

        userService.addUser(user);
    }


    @Test
    public void getUsers_shouldReturnAListOfUsers() {
        List<User> userList = aListOfUsers();
        when(userRepository.getUsers()).thenReturn(userList);

        List<User> createdList = userService.getUsers();

        assertThat(createdList).isEqualTo(userList);
    }

    @Test
    public void userExistsWithAValidUser_ShouldReturnTrue() {
        User user = aUserWith(NAME);
        when(userRepository.userExists(user)).thenReturn(true);

        boolean doExists = userService.userExists(user);
        verify(userRepository).userExists(user);

        assertThat(doExists).isTrue();
    }

    @Test
    public void userExistsWithANoExistingUser_ShouldReturnFalse(){
        User user = aUserWith(NAME);
        when(userRepository.userExists(user)).thenReturn(false);

        boolean doExists = userService.userExists(user);

        assertThat(doExists).isFalse();
    }

    @Test  //---- GOOD TEST ----
    public void givenAValidUser_shouldFindTheUser() {
        when(userRepository.findUserByName(NAME)).thenReturn(aUserWith(NAME));

        User foundUser = userService.findUserByName(NAME);

        assertThat(foundUser).isEqualTo(aUserWith(NAME));
    }

    @Test
    public void givenAnInvalidUser_shouldReturnNull() {
        when(userRepository.findUserByName(anyString())).thenThrow(EmptyResultDataAccessException.class);

        User foundUser = userService.findUserByName(NAME);

        assertThat(foundUser).isNull();
    }

    private User aUserWith(String name) {
        User user = new User();
        user.setName(name);
        user.setId(23423423);
        user.setPassword("3245234");
        return user;
    }

    private List<User> aListOfUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = aUserWith("name1");
        User user2 = aUserWith("name2");
        User user3 = aUserWith("name3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }
}