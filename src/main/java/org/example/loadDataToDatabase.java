package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class loadDataToDatabase {
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

    public loadDataToDatabase(String url, String user, String password, String databaseName) throws Exception {
        this.url = url;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void loadDocumentTypeToDatabase(int id, String name, String description, int status) throws Exception {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO DocumentsType (id,name, description, status) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setInt(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        }


    }
//load simple type for test db
    public void loadSimpleDocumentsToDatabase() {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO DocumentsType (id,name, description, status) "
                    + "VALUES (?, ?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setInt(1, 1);
            ps.setString(2, "passport");
            ps.setString(3, "passport local country");
            ps.setInt(4, 1);
            ps.executeUpdate();

            ps.clearParameters();
            ps.setInt(1, 2);
            ps.setString(2, "international passport");
            ps.setString(3, "passport international for not resident user ");
            ps.setInt(4, 1);
            ps.executeUpdate();

            ps.clearParameters();
            ps.setInt(1, 3);
            ps.setString(2, "driverID");
            ps.setString(3, "Driver License ID ");
            ps.setInt(4, 1);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error fill table DocumentsType: " + e.getMessage());

        }
    }


    //    public void loadClientToDatabase(String firstName,String middleName,String lastName,String nickname,
    //    String birthday,String city ,String companyFullName,String companyShortName,
    //    String countryName, String email, String locale, int status, String createTime, String updateTime)

    public void loadClientToDatabase(String firstName, String middleName, String lastName, String nickname, String birthday, String city, String companyFullName, String companyShortName,
                                     String countryName, String email, String locale, String status, String createTime, String updateTime) throws Exception {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO DocumentsType (id,firstName, middleName, lastName, nickname, birthday, city,"
                    + " companyFullName, companyShortName, countryName, email, locale, status, createTime, updateTime) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setNull(1, java.sql.Types.INTEGER); // передача NULL для автоматической генерации значения в столбце id
            ps.setString(2, firstName);
            ps.setString(3, middleName);
            ps.setString(4, lastName);
            ps.setString(5, nickname);
            ps.setString(6, birthday);
            ps.setString(7, city);
            ps.setString(8, companyFullName);
            ps.setString(9, companyShortName);
            ps.setString(10, countryName);
            ps.setString(11, email);
            ps.setString(12, locale);
            ps.setString(13, status);
            ps.setString(14, createTime);
            ps.setString(15, updateTime);

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public void loadClientAddressToDatabase(Long clientId, String countryName, String city, String state, int postcode, String address, String createTime, String updateTime) throws Exception {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO DocumentsType (id, clientId, countryName, city, state, postcode, address, createTime, updateTime) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setLong(2, clientId);
            ps.setString(3, countryName);
            ps.setString(4, city);
            ps.setString(5, state);
            ps.setInt(6, postcode);
            ps.setString(7, address);
            ps.setString(8, createTime);
            ps.setString(9, updateTime);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void loadClientPhoneToDatabase(Long clientId, int confirmed, String phones, String createTime, String updateTime) throws Exception {
        try (Connection conn = DriverManager.getConnection(getUrl(), getUser(), getPassword())) {
            conn.setCatalog(getDatabaseName());
            String insertData = "INSERT INTO DocumentsType (id, clientId, confirmed, phones, createTime, updateTime) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insertData);
            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setLong(2, clientId);
            ps.setInt(3, confirmed);
            ps.setString(4, phones);
            ps.setString(5, createTime);
            ps.setString(6, updateTime);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}


