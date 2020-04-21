package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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


    public User addUser(User user) {
        log.info("addUser(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword());
        jdbcTemplate.update("INSERT INTO users (u_name, u_password) VALUES ( :name , :password);", namedParameters);
        log.info("User: " + user.getName() + ", added.");

        return user;
    }

    public List<User> getUsers() {
        log.info("getUser(): Method called");
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        log.info("Getting the list of all users");
        return listUsers;
    }

    public boolean userExists(User requestedUser) {//todo change exception to service?
        log.info("validateUser(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", requestedUser.getName());
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.u_name= :name", namedParameters, new UserRowMapper());
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
//        log.info("validateUser(" + requestedUser + "): Requesting list of all users");
//        for (User user : listUsers) {
//            if (user.equals(requestedUser)) {
//                log.info("validateUser(" + requestedUser + "): User founded");
//                return true;
//            }
//        }
//        log.info("validateUser(" + requestedUser + "): Doesn't exist");
//        return false;
    }

    public User findUserByName(String name) {
        log.info("getUserByName(): Method called");
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name);
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.u_name= :name", namedParameters, new UserRowMapper());
        log.info("getUserByName(" + name + "): Requesting list of all users");

        return user;
    }

}
