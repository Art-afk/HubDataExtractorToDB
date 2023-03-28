package org.klimov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequestToApi {
    private final String apiUrl;
    private final String apiAccessToken;
    public String getApiUrl() {
        return apiUrl;
    }
    public String getApiAccessToken() {
        return apiAccessToken;
    }
    public GetRequestToApi(String apiUrl, String apiAccessToken) {
        this.apiUrl = apiUrl;
        this.apiAccessToken = apiAccessToken;
    }

    public String getRequestToClientApi(long getUserId) throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();

        String url = apiUrl + "/api/v2/clients/" + getUserId;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getApiAccessToken());
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder stringBuilder = new StringBuilder();

        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
            stringBuilder.append(output);
        }
        connection.disconnect();
        return stringBuilder.toString();

    }

    public String getRequestToPhonesApi(long getUserId) throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();

        String url = apiUrl + "/api/v2/phones?limit=10&filter[clientId]=" + getUserId;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getApiAccessToken());
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder stringBuilder = new StringBuilder();

        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
            stringBuilder.append(output);
        }
        connection.disconnect();
        return stringBuilder.toString();

    }


    public String getRequestToAddressApi(long getUserId) throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();

        String url = apiUrl + "/api/v2/addresses?limit=10&sort_by=createTime&filter[clientId]=" + getUserId;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getApiAccessToken());
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder stringBuilder = new StringBuilder();

        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
            stringBuilder.append(output);
        }
        connection.disconnect();
        return stringBuilder.toString();

    }

}

