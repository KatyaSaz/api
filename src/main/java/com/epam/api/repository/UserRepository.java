package com.epam.api.repository;

import com.epam.api.config.DataSourceConfig;
import com.epam.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getUsers(String table, DataSourceConfig.DataSourceProperties.MappingProperties mapping) {
        String sql = String.format("SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
                mapping.getId(), mapping.getUsername(), mapping.getName(), mapping.getSurname(), table);
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getString("id"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getString("surname")
        ));
    }
}
