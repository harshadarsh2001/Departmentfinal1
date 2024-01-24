package com.harshproject.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Health health() {
        if (isDatabaseConnectionHealthy()) {
            return Health.up().withDetail("message", "Database connection is healthy").build();
        } else {
            return Health.down().withDetail("message", "Database connection is not healthy").build();
        }
    }

    private boolean isDatabaseConnectionHealthy() {
        try {
            // Execute a simple query to check the health of the database
            jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class);
            return true;  // If successful, consider the database connection healthy
        } catch (Exception e) {
            return false; // If an exception occurs, consider the database connection unhealthy
        }
    }
}