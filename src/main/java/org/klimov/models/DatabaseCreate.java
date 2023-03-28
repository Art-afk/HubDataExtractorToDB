package org.klimov.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreate {
    private String url;
    private String user;
    private String password;
    private String databaseName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public DatabaseCreate(String url, String user, String password, String databaseName) throws Exception {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void createDatabase() {
        try (Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            String sql = "CREATE DATABASE IF NOT EXISTS " +getDatabaseName();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                System.out.println("Database " + getDatabaseName() + " created successfully");
            }


        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
}

}
