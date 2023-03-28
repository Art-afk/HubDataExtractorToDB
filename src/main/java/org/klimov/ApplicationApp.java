package org.klimov;

import org.klimov.models.DatabaseCreate;
import org.klimov.models.DatabaseTableCreate;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationApp {
    String dbUrl;
    String dbUser;
    String dbPassword;
    String dbName;
    //----------//
    String apiUser;
    String apiUrl;
    String apiPassword;
    String apiAcessToken;



    public void Start() throws Exception {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        props.load(inputStream);
        setDbUrl(props.getProperty("db.url"));
        setDbUser(props.getProperty("db.userName"));
        setDbPassword(props.getProperty("db.password"));
        setDbName(props.getProperty("db.name"));
        setApiUrl(props.getProperty("api.url"));
        setApiPassword(props.getProperty("api.password"));
        setApiUser(props.getProperty("api.user"));

        GetAccessToken getAccessToken = new GetAccessToken(getApiUrl(), getApiUser(), getApiPassword());
        setApiAcessToken(getAccessToken.handShake());
        DatabaseCreate databaseCreate = new DatabaseCreate(dbUrl, dbUser, dbPassword, dbName);
        databaseCreate.createDatabase();
        DatabaseTableCreate databaseTableCreate = new DatabaseTableCreate(databaseCreate.getUrl(), databaseCreate.getUser(), databaseCreate.getPassword(), databaseCreate.getDatabaseName());
        databaseTableCreate.createTables();
        GetNumberOflClients getNumberOflClients = new GetNumberOflClients(apiUrl, apiAcessToken);
        GetRequestToApi getRequestToApi = new GetRequestToApi(apiUrl, apiAcessToken);
        ParsingJson parsingJson = new ParsingJson(apiUrl, apiAcessToken,dbName,dbUrl,dbPassword,dbUser);
        long clientsId = getNumberOflClients.getTotalClients();
        for (long i = 1; i <= clientsId; i++) {
            System.out.println("myId: " + i);
            parsingJson.parsingAndLoadClientToDatabase(getRequestToApi.getRequestToClientApi(i));
            parsingJson.parsingAndLoadPhoneClientsToDatabase(getRequestToApi.getRequestToPhonesApi(i));
            parsingJson.parsingAndLoadAddressToDatabase(getRequestToApi.getRequestToAddressApi(i));

        }

        //StartLoadClients startLoadClients = new StartLoadClients();
        //startLoadClients.iterateAllClients(getNumberOflClients.getTotalClients());

        // GetDocoments getDocoments = new GetDocoments(apiUrl,apiAcessToken);
        //getDocoments.all();
        CreateClients createClients = new CreateClients(dbUrl, dbUser, dbPassword, dbName);

        //createUser.createSampleUserInDb();

        //CreateDocumentType createDocumentType = new CreateDocumentType(dbUrl,dbUser,dbPassword,dbName);
        //   GetDocumentsType getDocumentsType = new GetDocumentsType(getApiUrl(),getApiAcessToken());
        // SetDocumentsTypeToDatabase setDocumentsTypeToDatabase = new SetDocumentsTypeToDatabase(getApiUrl(),getApiAcessToken());
        //  setDocumentsTypeToDatabase.parsing(getDocumentsType.get(),dbName,dbUrl,dbPassword,dbUser);

    }


    public String getApiUser() {
        return apiUser;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public String getApiAcessToken() {
        return apiAcessToken;
    }

    public void setApiAcessToken(String apiAcessToken) {
        this.apiAcessToken = apiAcessToken;

    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }
}
