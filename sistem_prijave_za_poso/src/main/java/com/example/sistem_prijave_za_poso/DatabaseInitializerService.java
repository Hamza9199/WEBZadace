package com.example.sistem_prijave_za_poso;
 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DatabaseInitializerService {

    @Value("${spring.datasource.url}")
    private String serverUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private final String databaseName = "projekat_baza"; 

    @PostConstruct
    public void initializeDatabase() {
        try {
            Connection connection = DriverManager.getConnection(serverUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            statement.close();
            connection.close();

            System.out.println("Baza podataka '" + databaseName + "' je uspešno kreirana (ako ranije nije postojala).");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Greška prilikom kreiranja baze podataka: " + e.getMessage());
        }
    }
}
