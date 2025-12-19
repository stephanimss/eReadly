package com.ereadly.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConfig {
    private static Properties props = new Properties();

    static {
        try (InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(is);
            Class.forName(props.getProperty("db.driver"));
        } catch (Exception e) {
            System.err.println("Gagal memuat db.properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );
    }
}