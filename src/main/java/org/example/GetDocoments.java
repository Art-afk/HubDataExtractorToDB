package org.example;

import javax.xml.parsers.SAXParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetDocoments {
    private final String apiUrl;

    private final String apiAccessToken;

    public GetDocoments(String apiUrl, String apiAccessToken) {
        this.apiUrl = apiUrl;

        this.apiAccessToken = apiAccessToken;
    }
    public String getApiUrl() {
        return apiUrl;
    }



    public void all() throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();
   /*
        curl --location -g --request GET 'https://host.name/api/v2/documents?limit=10&offset=0&filter[clientId]=1' \
--header 'Authorization: Bearer <token>'
         */
        String endpoint = getApiUrl() + "/api/v2/documents";
        Map<String, String> params = new HashMap<>();
        params.put("filter[clientId]", "4");
        //params.put("filter[emails]", "foo@bar.com");
        String url = endpoint + "?" + encodeParameters.encode(params);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiAccessToken);

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        System.out.println("Documents: ");
        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
        }
        connection.disconnect();

    }
}
