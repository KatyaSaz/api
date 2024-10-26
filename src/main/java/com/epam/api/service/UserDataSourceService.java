package com.epam.api.service;

import com.epam.api.config.DataSourceConfig;
import com.epam.api.model.User;
import com.epam.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDataSourceService {

    private final Map<String, DataSource> dataSources;
    private final DataSourceConfig config;

    @Autowired
    public UserDataSourceService(@Qualifier("dataSourceConfig") DataSourceConfig config) {
        this.config = config;
        this.dataSources = new HashMap<>();
        config.getDataSources().forEach(this::initializeDataSource);
    }

    private void initializeDataSource(DataSourceConfig.DataSourceProperties properties) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder
                .url(properties.getUrl())
                .username(properties.getUser())
                .password(properties.getPassword())
                .driverClassName(properties.getDriverClassName()); // Set the driver class name

        dataSources.put(properties.getName(), dataSourceBuilder.build());
    }


    public List<User> getUsersFromAllSources() {
        List<User> allUsers = new ArrayList<>();
        for (DataSourceConfig.DataSourceProperties properties : config.getDataSources()) {
            DataSource dataSource = dataSources.get(properties.getName());
            List<User> users = new UserRepository(dataSource).getUsers(properties.getTable(), properties.getMapping());
            allUsers.addAll(users);
        }
        return allUsers;
    }
}

