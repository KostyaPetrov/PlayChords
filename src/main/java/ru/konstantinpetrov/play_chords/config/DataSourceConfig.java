package ru.konstantinpetrov.play_chords.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.*;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("kostya");
        ds.setPassword("12345");
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }
}
