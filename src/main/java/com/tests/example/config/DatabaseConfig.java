package com.tests.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.postgres}")
    private String postgresDatasource;

    @Bean
    public HikariDataSource postgresDataSource() {
        try {
            final HikariConfig config = new HikariConfig(postgresDatasource);
            return new HikariDataSource(config);
        }
        catch (Exception ex){
            return new HikariDataSource();
        }
    }
}
