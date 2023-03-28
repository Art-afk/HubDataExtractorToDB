package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateClients {
    private String url;
    private String user;
    private String password;
    private String databaseName;

    private String name;
    private String lastName;
    private String middleName;
    private String nickname;
    private String birthday;
    private String phone;
    private String email;
    private String state;
    private String postalCode;
    private String address;
    private String city;
    private String country;
    private String locale;
    private String status;


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

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
    public String getLocale() {
        return locale;
    }

    public String getStatus() {
        return status;
    }

    public CreateClients(String url, String user, String password, String databaseName, String name, String lastName,
                         String middleName, String nickname, String birthday, String phone, String email, String state,
                         String postalCode, String address, String city, String country, String locale, String status) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nickname = nickname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.state = state;
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
        this.country = country;
        this.locale = locale;
        this.status = status;
    }

    public CreateClients(String url, String user, String password, String databaseName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void createSampleClientsInDb() throws Exception {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO Users (name, lastName, middleName, nickname, birthday, phone, email, state, postalCode, address, city, country, locale, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertData);

            // Добавление первой записи
            ps.setString(1, "Иван");
            ps.setString(2, "Иванов");
            ps.setString(3, "Иванович");
            ps.setString(4, "ivan88");
            ps.setString(5, "01.01.1988");
            ps.setString(6, "1234567890");
            ps.setString(7, "ivanov@mail.com");
            ps.setString(8, "Москва");
            ps.setString(9, "123456");
            ps.setString(10, "ул. Ленина, д. 1");
            ps.setString(11, "Москва");
            ps.setString(12, "Россия");
            ps.setString(13, "Ru_ru");
            ps.setString(14, "активный");
            ps.executeUpdate();

        }
        catch (SQLException e){
            System.err.println("Error fill createSampleUserInDb table: " + e.getMessage());

        }
    }
}
