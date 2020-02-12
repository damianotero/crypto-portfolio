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
    public void setUp() throws Exception {
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
    public void whenAddingAUserThatAlreadyExists_throwsUserAlreadyExistsException(){
        User user = aUserWith(NAME);
        when(userRepository.addUser(user)).thenThrow(DataIntegrityViolationException.class);

<<<<<<< HEAD

=======
       userService.addUser(user);
>>>>>>> 44bbe2fada434650f511547e96ec05d3164167d5
    }


    @Test
    public void getUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("test name");
        userList.add(user);

<<<<<<< HEAD
        when(userRepository.getUsers()).thenReturn(userList);

        userList= userRepository.getUsers();
        assertThat(userList.get(0).getName()).isEqualTo("test name");
        
=======
        List<User> createdList = userService.getUsers();

        assertThat(createdList).isEqualTo(userList);
>>>>>>> 44bbe2fada434650f511547e96ec05d3164167d5
    }

    @Test
    public void validateUser() {
<<<<<<< HEAD
        User user = new User();
        user.setName("test name");
        user.setId(23423423);
        user.setPassword("3245234");
        userService.validateUser(user);
        verify(userRepository).validateUser(user);

        User user2 = new User();
        user2.setName("test name");
        user2.setId(23423423);
        user2.setPassword("3245234");
        assertThat(userService.validateUser(user2)).isEqualTo(true);
=======
        User user = aUserWith(NAME);

        userService.userExists(user);
        verify(userRepository).userExists(user);
    }

    @Test  //---- GOOD TEST ----
    public void givenAValidUser_shouldFindTheUser() {
        when(userRepository.findUserByName(NAME)).thenReturn(aUserWith(NAME));

        User foundUser = userService.findUserByName(NAME);

        assertThat(foundUser).isEqualTo(aUserWith(NAME));
>>>>>>> 44bbe2fada434650f511547e96ec05d3164167d5
    }

    @Test
    public void givenAnInvalidUser_shouldReturnNull(){
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
}