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

    @Value("${database.name}")
    private String databaseName;

    private final String newUsername = "novi_korisnik"; 
    private final String newPassword = "nova_lozinka";   

    @PostConstruct
    public void initializeDatabase() {
        try {
            createDatabaseIfNotExists();

            createNewUserWithPrivileges();

            System.out.println("Inicijalizacija baze i kreiranje korisnika je završeno.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Greška prilikom inicijalizacije baze: " + e.getMessage());
        }
    }

    private void createDatabaseIfNotExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(serverUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            System.out.println("Baza podataka '" + databaseName + "' je kreirana (ako ranije nije postojala).");
        }
    }

    private void createNewUserWithPrivileges() throws SQLException {
        try (Connection connection = DriverManager.getConnection(serverUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {

            String createUserQuery = "CREATE USER IF NOT EXISTS '" + newUsername + "'@'localhost' IDENTIFIED BY '" + newPassword + "'";
            statement.executeUpdate(createUserQuery);

            String grantPrivilegesQuery = "GRANT ALL PRIVILEGES ON " + databaseName + ".* TO '" + newUsername + "'@'localhost'";
            statement.executeUpdate(grantPrivilegesQuery);

            statement.executeUpdate("FLUSH PRIVILEGES");

            System.out.println("Novi korisnik '" + newUsername + "' je kreiran sa pristupom bazi '" + databaseName + "'.");
        }
    }
}
