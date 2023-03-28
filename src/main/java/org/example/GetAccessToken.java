package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAccessToken {

    private String ApiUrl;
    private String ApiUser;
    private String apiPassword;

    public String getApiUser() {
        return ApiUser;
    }

    public String getApiUrl() {
        return ApiUrl;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public GetAccessToken(String apiUrl, String apiUser, String apiPassword) {

        ApiUrl = apiUrl;
        ApiUser = apiUser;
        this.apiPassword = apiPassword;
    }


    public String handShake() throws Exception {
        String response = getApiToken();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree((response));
        boolean is2FARequired = json.get("2faRequired").asBoolean();
        String accessToken = json.get("accessToken").get("token").asText();
        String createdAT = json.get("accessToken").get("createdAt").asText();
        String expiresAt = json.get("accessToken").get("expiresAt").asText();
        String refreshToken = json.get("refreshToken").get("token").asText();
        String refreshCreatedAt = json.get("refreshToken").get("createdAt").asText();
        String refreshExpiresAt = json.get("refreshToken").get("expiresAt").asText();
        System.out.println("2FA Required: " + is2FARequired);
        System.out.println("Access Token: " + accessToken);
        System.out.println("Created At: " + createdAT);
        System.out.println("Expires At: " + expiresAt);
        System.out.println("Refresh Access Token: " + refreshToken);
        System.out.println("Refresh Created At: " + refreshCreatedAt);
        System.out.println("Refresh Expires At: " + refreshExpiresAt);

        return accessToken;

    }

    private String getApiToken() throws Exception {
        URL url = new URL(getApiUrl() + "/api/v2/signin");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        String jsonInputString = "{\"email\": \"" + getApiUser() + "\", \"password\": \"" + getApiPassword() + "\"}";

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String output;
        System.out.println("output from server...\n");

        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
            stringBuilder.append(output);

        }
        connection.disconnect();

        return stringBuilder.toString();
    }




}
