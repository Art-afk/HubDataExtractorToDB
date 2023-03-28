package org.example.models;

import java.sql.*;

public class DatabaseTableCreate {
    private String url;
    private String user;
    private String password;
    private String databaseName;



    public DatabaseTableCreate(String url, String user, String password, String databaseName) throws Exception {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void createTables() throws Exception {
        try (Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            String tableClients = "Users";
            String tableDocuments = "Documents";
            String tableDocumentsType = "DocumentsType";
            String tableAddress = "Address";
            String tablePhones = "Phones";

            boolean tableClientsExists = false;
            boolean tableDocumentsExists = false;
            boolean tableDocumentsTypeExists = false;
            boolean tableAddressExists = false;
            boolean tablePhonesExists = false;

            // Проверяем существование таблицы
            try (ResultSet ttablesName = connection.getMetaData().getTables(null, null, tableClients, null)) {
                connection.setCatalog(getDatabaseName());
                if (ttablesName.next()) {
                    System.out.println("Table " + tableClients + " already exists");
                    tableClientsExists = true;
                }
                try (ResultSet ttableDocuments = connection.getMetaData().getTables(null, null, tableDocuments, null)) {
                    connection.setCatalog(getDatabaseName());
                    if (ttableDocuments.next()) {
                        System.out.println("Table " + tableDocuments + " already exists");
                        tableDocumentsExists = true;
                    }
                    try (ResultSet ttableDocumentsType = connection.getMetaData().getTables(null, null, tableDocumentsType, null)) {
                        connection.setCatalog(getDatabaseName());
                        if (ttableDocumentsType.next()) {
                            System.out.println("Table " + tableDocumentsType + " already exists");
                            tableDocumentsTypeExists = true;
                        }
                        try (ResultSet ttableAddress = connection.getMetaData().getTables(null, null, tableAddress, null)) {
                            connection.setCatalog(getDatabaseName());
                            if (ttableAddress.next()) {
                                System.out.println("Table " + tableAddress + " already exists");
                                tableAddressExists = true;
                            }
                            try (ResultSet ttablePhones = connection.getMetaData().getTables(null, null, tablePhones, null)) {
                                connection.setCatalog(getDatabaseName());
                                if (ttablePhones.next()) {
                                    System.out.println("Table " + tablePhones + " already exists");
                                    tablePhonesExists = true;
                                }

                            }
                        }
                    }
                }
            }
            //    public void loadClientToDatabase(String firstName,String middleName,String lastName,String nickname,
            //    String birthday,String city,String ,String companyFullName,String companyShortName,
            //    String countryName, String email, String locale, int status, String createTime, String updateTime)
                if (!tableClientsExists) {
                    String sql = "CREATE TABLE Clients ("
                            + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                            + "firstName VARCHAR(255),"
                            + "middleName VARCHAR(255),"
                            + "lastName VARCHAR(255),"
                            + "nickname VARCHAR(255),"
                            + "birthday VARCHAR(255),"
                            + "city VARCHAR(255),"
                            + "companyFullName VARCHAR(255),"
                            + "companyShortName VARCHAR(255),"
                            + "countryName VARCHAR(255),"
                            + "email VARCHAR(255) ,"
                            + "locale VARCHAR(255) ,"
                            + "status VARCHAR(255),"
                            + "createTime VARCHAR(255),"
                            + "updateTime VARCHAR(255)"
                            + ")";
                    try (Statement statement = connection.createStatement()) {
                        connection.setCatalog(databaseName);
                        statement.executeUpdate(sql);
                        System.out.println("Table " + tableClients + " created successfully");
                    }
                }
                if (!tableDocumentsTypeExists) {
                    String sql = "CREATE TABLE DocumentsType ("
                            + "id BIGINT PRIMARY KEY,"
                            + "name VARCHAR(255) NOT NULL,"
                            + "description VARCHAR(255),"
                            + "status TINYINT"
                            + ")";
                    try (Statement statement = connection.createStatement()) {
                        connection.setCatalog(databaseName);
                        statement.executeUpdate(sql);
                        System.out.println("Table " + tableDocumentsType + " created successfully");
                    }
                }
                if (!tableDocumentsExists) {
                    String sql = "CREATE TABLE Documents ("
                            + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                            + "clientId BIGINT NOT NULL," + "FOREIGN KEY (clientId) REFERENCES Clients(id),"
                            + "documentType INT NOT NULL," + "FOREIGN KEY (documentType) REFERENCES DocumentsType(id),"
                            + "document LONGBLOB"
                            + ")";
                    try (Statement statement = connection.createStatement()) {
                        connection.setCatalog(databaseName);
                        statement.executeUpdate(sql);
                        System.out.println("Table " + tableDocuments + " created successfully");
                    }
                }
            if (!tableAddressExists) {
                String sql = "CREATE TABLE Address ("
                        + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                        + "clientId BIGINT NOT NULL," + "FOREIGN KEY (clientId) REFERENCES Clients(id),"
                        + "countryName VARCHAR(255),"
                        + "city VARCHAR(255),"
                        + "state VARCHAR(255),"
                        + "postcode INT,"
                        + "address VARCHAR(255),"
                        + "createTime VARCHAR(255),"
                        + "updateTime VARCHAR(255)"

                        + ")";
                try (Statement statement = connection.createStatement()) {
                    connection.setCatalog(databaseName);
                    statement.executeUpdate(sql);
                    System.out.println("Table " + tableAddress + " created successfully");
                }
            }
            if (!tablePhonesExists) {
                String sql = "CREATE TABLE Phones ("
                        + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                        + "clientId BIGINT NOT NULL," + "FOREIGN KEY (clientId) REFERENCES Clients(id),"
                        + "confirmed TINYINT,"
                        + "phones VARCHAR(255)"
                        + "createTime VARCHAR(255)"
                        + "updateTime VARCHAR(255)"

                        + ")";
                try (Statement statement = connection.createStatement()) {
                    connection.setCatalog(databaseName);
                    statement.executeUpdate(sql);
                    System.out.println("Table " + tablePhones + " created successfully");
                }
            }

            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());

            }
        }

}
