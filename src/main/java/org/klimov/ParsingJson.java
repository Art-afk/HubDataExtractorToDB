package org.klimov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParsingJson {
    private String url;
    private String accessToken;

    String dbName;
    String dbUrl;
    String dbPassword;
    String dbUserName;

    public String getUrl() {
        return url;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public ParsingJson(String url, String accessToken, String dbName, String dbUrl, String dbPassword, String dbUserName) {
        this.url = url;
        this.accessToken = accessToken;
        this.dbName = dbName;
        this.dbUrl = dbUrl;
        this.dbPassword = dbPassword;
        this.dbUserName = dbUserName;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }




    public void parsingAndLoadOneDocumentTypeToDatabase(String response, String dbName, String dbUrl, String dbPassword, String dbUserName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        loadDataToDatabase loadDataToDatabase = new loadDataToDatabase(dbUrl, dbUserName, dbPassword, dbName);

        JsonNode json = mapper.readTree((response));
        int totalTypes = json.get("total").asInt();
        System.out.println("all types: " + totalTypes);
        for (int i = 0; i <= totalTypes - 1; i++) {
            int typeId = json.get("data").get(i).get("id").asInt();
            String name = json.get("data").get(i).get("name").asText();
            String description = json.get("data").get(i).get("description").asText();
            boolean status = json.get("data").get(i).get("enabled").asBoolean();
            System.out.println("id: " + typeId);
            System.out.println("name: " + name);
            System.out.println("description: " + description);
            System.out.println("status: " + status);
            loadDataToDatabase.loadDocumentTypeToDatabase(typeId, name, description, status ? 1 : 0);
        }


    }


    //    public void loadClientToDatabase(String firstName,String middleName,String lastName,String nickname,
    //    String birthday,String city ,String companyFullName,String companyShortName,
    //    String countryName, String email, String locale, int status, String createTime, String updateTime)

    public void parsingAndLoadClientToDatabase(String response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree((response));
        long clientId;
        String firstName;
        String middleName;
        String lastName;
        String nickname;
        String birthday;
        String city;
        String companyFullName;
        String companyShortName;
        String countryName;
        String email;
        String locale;
        String status;
        String createTime;
        String updateTime;

        clientId = json.get("clientId").asLong();
        birthday = json.get("birthday").asText();
        firstName = json.get("firstName").asText();
        middleName = json.get("middleName").asText();
        lastName = json.get("lastName").asText();
        nickname = json.get("nickname").asText();
        city = json.get("city").asText();
        companyFullName = json.path("company").path("fullName").asText();
        companyShortName = json.path("company").path("shortName").asText();
        countryName = json.path("country").path("countryName").asText();
        email = json.get("email").asText();
        locale = json.get("locale").asText();
        status = json.get("status").asText();
        createTime = json.get("createTime").asText();
        updateTime = json.get("updateTime").asText();

        System.out.println("clientId: "  + clientId);
        System.out.println("birthday: " + birthday);
        System.out.println("firstName: " + firstName);
        System.out.println("middleName: " + middleName);

        System.out.println("lastName: " + lastName);
        System.out.println("nickname: " + nickname);
        System.out.println("middleName: " + middleName);
        System.out.println("city: " + city);
        System.out.println("companyFullName: " + companyFullName);
        System.out.println("companyShortName: " + companyShortName);
        System.out.println("countryName: " + countryName);
        System.out.println("email: " + email);
        System.out.println("locale: " + locale);
        System.out.println("status: " + status);
        System.out.println("createTime: " + createTime);
        System.out.println("updateTime: " + updateTime);
        //  loadDataToDatabase.loadDocumentTypeToDatabase(typeId,name,description,status ? 1:0);

    }

    public void parsingAndLoadPhoneClientsToDatabase(String response)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree((response));
        int totalId;
        totalId = json.get("total").asInt();
        if(totalId !=0) {
            for (int i = 0; i <= totalId -1 ; i++) {
                boolean confirmed = json.get("data").get(i).get("confirmed").asBoolean();
                String number = json.get("data").get(i).get("number").asText();
                String createTime = json.get("data").get(i).get("createTime").asText();
                String updateTime = json.get("data").get(i).get("updateTime").asText();
                System.out.println("-------Phones---------");
                System.out.println("confirmed: " + confirmed);
                System.out.println("number: " + number);
                System.out.println("createTime: " + createTime);
                System.out.println("updateTime: " + updateTime);
                //  loadDataToDatabase.loadDocumentTypeToDatabase(typeId, name, description, status ? 1 : 0);
            }
        }else System.out.println("totalPhone is Empty = 0");
    }

    //            String insertData = "INSERT INTO DocumentsType (id, clientId, countryName, city, state, postcode, address, createTime, updateTime) " +
    public void parsingAndLoadAddressToDatabase(String response)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree((response));
        int totalId;
        totalId = json.get("total").asInt();
        if(totalId !=0) {
            for (int i = 0; i <= totalId -1 ; i++) {
                String address = json.get("data").get(i).get("address").asText();
                String countryName = json.path("data").path(i).path("country").path("countryName").asText();
                String postcode = json.get("data").get(i).get("postcode").asText();
                String state = json.get("data").get(i).get("state").asText();
                String city = json.get("data").get(i).get("city").asText();
                String clientId = json.get("data").get(i).get("clientId").asText();
                String createTime = json.get("data").get(i).get("createTime").asText();
                String updateTime = json.get("data").get(i).get("updateTime").asText();

                System.out.println("-------Address---------");
                System.out.println("address: " + address);
                System.out.println("countryName: " + countryName);
                System.out.println("postcode: " + postcode);
                System.out.println("state: " + state);
                System.out.println("city: " + city);
                System.out.println("clientId: " + clientId);
                System.out.println("createTime: " + createTime);
                System.out.println("updateTime: " + updateTime);
                //  loadDataToDatabase.loadDocumentTypeToDatabase(typeId, name, description, status ? 1 : 0);
            }
        }else System.out.println("totalPhone is Empty = 0");
    }
}
