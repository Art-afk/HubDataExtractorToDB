package org.klimov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetNumberOflClients {
    private String apiUrl;
    private String user;
    private String password;
    private String apiAccessToken;

    public String getApiUrl() {
        return apiUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getApiAccessToken() {
        return apiAccessToken;
    }

    public GetNumberOflClients(String apiUrl, String apiAccessToken) throws Exception {
        this.apiUrl = apiUrl;
        this.apiAccessToken = apiAccessToken;
    }

    public long getTotalClients() throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();
        String endpoint = apiUrl + "/api/v2/clients";
        Map<String, String> params = new HashMap<>();
        params.put("limit", "1");
        //  params.put("offset", "0");
        //  params.put("sort_order", "desc");
        //  params.put("sort_by", "createTime");
        // params.put("filter[clientId]", "4");
      /*  params.put("filter[emails]", "foo@bar.com");
        params.put("filter[name]", "Sergey");
        params.put("filter[nickname]", "Voronov");
        params.put("filter[managerIds]", "1, 2, 3");
        params.put("filter[countryCodes]", "1, 2, 3");
        params.put("filter[city]", "New York");
        params.put("filter[statusIds]", "1, 2, 3");
        params.put("filter[internalTypeIds]", "1, 2, 3");
        params.put("filter[companyShortName]", "Company name");
        params.put("filter[companyFullName]", "Company full name");
        params.put("filter[riskLevelIds]", "1, 2, 3");
        params.put("filter[typeIds]", "1, 2, 3");
        params.put("filter[verificationLevelIds]", "-96750485");
*/
        String url = endpoint + "?" + encodeParameters.encode(params);
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
            System.out.println("DEBUG:" + output);
            stringBuilder.append(output);
        }
        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(stringBuilder.toString());
        long totalClients = json.get("total").asLong();
        System.out.println("DEBUG: all types: " + totalClients);

        return totalClients;
    }
}