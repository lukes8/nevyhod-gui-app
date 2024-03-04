package com.lukepeace.projects.nevyhodcore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ItemDao {
    @Autowired private JdbcTemplate jdbcTemplate;

    public Long getMaxId() {
        return null;
    }
}
