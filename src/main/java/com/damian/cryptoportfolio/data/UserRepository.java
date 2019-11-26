package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void addUser(User user) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("password", user.getPassword());
        jdbcTemplate.update("INSERT INTO users (u_name, u_password) VALUES ( :name , :password);", namedParameters);

    }

    public List<User> getUsers() {
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());

        return listUsers;
    }

    public boolean validateUser(User requestedUser) {
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());

        for (User user : listUsers) {
            if (user.equals(requestedUser)) {

                return true;
            }
        }
        return false;
    }

    public User getUserByName( String name) {
        List<User> listUsers = jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());

        for (User user : listUsers) {
            if (user.getName().equalsIgnoreCase(name)) {

                return user;
            }
        }
        return null;
    }

}
