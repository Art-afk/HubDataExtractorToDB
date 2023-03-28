package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDocument {
    private String url;
    private String user;
    private String password;
    private String databaseName;


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

    public CreateDocument(String url, String user, String password, String databaseName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void createSampleDocument(){
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO Documents (name, type,Collation) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setString(1, "name_photo.jpg");
            ps.setString(2, "passport");
            ps.executeUpdate();



        }catch (Exception e){
        System.out.println("Exception createSampleDocument: " + e);
    }
    }
}
