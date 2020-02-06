package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void addUser(User user) {
        log.info("addUser(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword());
        jdbcTemplate.update("INSERT INTO users (u_name, u_password) VALUES ( :name , :password);", namedParameters);
        log.info("User: " + user.getName() + ", added.");

    }

    public List<User> getUsers() {
        log.info("getUser(): Method called");
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        log.info("Getting the list of all users");
        return listUsers;
    }

    public boolean validateUser(User requestedUser) {
        log.info("validateUser(): Method called");
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        log.info("validateUser(" + requestedUser + "): Requesting list of all users");
        for (User user : listUsers) {
            if (user.equals(requestedUser)) {
                log.info("validateUser(" + requestedUser +"): User founded");
                return true;
            }
        }
        log.info("validateUser(" + requestedUser +"): Doesn't exist");
        return false;
    }

    public User getUserByName(String name) {
        log.info("getUserByName(): Method called");
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        log.info("getUserByName("+name+"): Requesting list of all users");

        for (User user : listUsers) {
            if (user.getName().equalsIgnoreCase(name)) {
                log.info("getUserByName("+name+"): User founded");
                return user;
            }
        }
        log.info("getUserByName("+name+"): Doesn't exists");
        return null;
    }

}
