package com.epam.api.service.impl;

import com.epam.api.config.DataSourceConfig;
import com.epam.api.model.User;
import com.epam.api.service.DataSourceHandler;
import com.epam.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final DataSourceConfig config;
    private final DataSourceHandler sourceHandler;

    @Autowired
    public UserServiceImpl(@Qualifier("dataSourceConfig") DataSourceConfig config) {
        this.config = config;
        this.sourceHandler = new DataSourceHandler(config);
    }

    private List<User> getUsers(DataSourceConfig.DataSourceProperties properties) {
        return sourceHandler.getRepositories().get(properties.getName())
                .getUsers(properties.getTable(), properties.getMapping());
    }

    @Override
    public List<User> findAll() {
        return config.getDataSources().stream()
                .flatMap(properties -> getUsers(properties).stream())
                .collect(Collectors.toList());
    }
}

