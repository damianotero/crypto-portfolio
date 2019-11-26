package com.damian.cryptoportfolio.data;

import com.damian.cryptoportfolio.logic.models.Coin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinRowMapper implements RowMapper<Coin> {

    @Override
    public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {

        Coin coin = new Coin();

        coin.setId(rs.getInt("c_id"));
        coin.setName(rs.getString("c_name"));
        coin.setToken(rs.getString("c_token"));
        coin.setAmount(rs.getDouble("c_amount"));
        coin.setUserId(rs.getInt("c_u_id"));
        return coin;
    }
}
