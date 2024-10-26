package com.epam.api.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app")
public class DataSourceConfig {

    private List<DataSourceProperties> dataSources;

    @Data
    @NoArgsConstructor
    public static class DataSourceProperties {
        private String name;
        private String url;
        private String table;
        private String user;
        private String password;
        private String driverClassName;
        private MappingProperties mapping;

        @Data
        @NoArgsConstructor
        public static class MappingProperties {
            private String id;
            private String username;
            private String name;
            private String surname;

        }
    }

}
