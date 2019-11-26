package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user =new User();

        user.setId(rs.getInt("u_id"));
        user.setName(rs.getString("u_name"));
        user.setPassword(rs.getString("u_password"));

        return user;
    }
}
