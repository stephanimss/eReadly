/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ereadly.config;

/**
 *
 * @author Luluil Maknun
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

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