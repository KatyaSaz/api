package com.epam.api.service;

import com.epam.api.config.DataSourceConfig;
import com.epam.api.repository.UserRepository;
import lombok.Getter;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Getter
public class DataSourceHandler {

    private final Map<String, UserRepository> repositories;

    public DataSourceHandler(DataSourceConfig config) {
       this.repositories = new HashMap<>();
       initializeDataSourcesAndRepositories(config);
    }


    private DataSource createDataSource(DataSourceConfig.DataSourceProperties properties) {
        return DataSourceBuilder.create()
                .url(properties.getUrl())
                .username(properties.getUser())
                .password(properties.getPassword())
                .driverClassName(properties.getDriverClassName())
                .build();
    }

    private void initializeDataSourcesAndRepositories(DataSourceConfig config) {
        config.getDataSources().forEach(properties -> {
            DataSource dataSource = createDataSource(properties);
            repositories.put(properties.getName(), new UserRepository(dataSource));
        });
    }

}
