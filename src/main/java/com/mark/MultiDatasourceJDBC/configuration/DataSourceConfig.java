package com.mark.MultiDatasourceJDBC.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "todos.datasource")
    public DataSource todosDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "topics.datasource")
    public DataSource topicsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate todosJdbcTemplate(@Qualifier("todosDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate topicsJdbcTemplate(@Qualifier("topicsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
