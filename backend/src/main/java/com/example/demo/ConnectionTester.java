package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConnectionTester {

    private static final Logger log = LoggerFactory.getLogger(ConnectionTester.class);


    private String buildUrl(ConnectionRequest req) {
        return "jdbc:postgresql://%s:%d/%s".formatted(req.host(), req.port(), req.database());
    }


    public boolean test(ConnectionRequest req) {
        var props = new Properties();
        props.setProperty("user", req.username());
        props.setProperty("password", req.password());
        props.setProperty("connectTimeout", "5");   // seconds, TCP handshake
        props.setProperty("loginTimeout", "5");     // seconds, auth
        try (Connection conn = DriverManager.getConnection(buildUrl(req), props)) {
            return conn.isValid(5);
        } catch (SQLException e) {
            log.warn("Connection test failed for {}:{} — SQLState {}: {}",
                req.host(), req.port(), e.getSQLState(), e.getMessage());
            return false;
        }
    }
}