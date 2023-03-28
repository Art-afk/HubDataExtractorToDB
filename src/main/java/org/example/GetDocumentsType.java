package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GetDocumentsType {
    private final String url;
    private final String accessToken;

    public String getUrl() {
        return url;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public GetDocumentsType(String url, String accessToken) {
        this.url = url;
        this.accessToken = accessToken;
    }
    //sample json:
    //{"total":8,"data":[{"id":2,"description":"National ID \/ Passport","enabled":true,"example":"","group":{"id":2,"description":"Proof of identity","enabled":true,"name":"Proof of identity","priority":1,"resources":[{"key":"caption","locale":"ru_RU","value":"\u0423\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435 \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"},{"key":"description","locale":"ru_RU","value":"\u0423\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435 \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"}],"type":"one"},"maxFiles":3,"name":"National ID \/ Passport","priority":1,"resources":[]},{"id":3,"description":" Driving license","enabled":true,"example":"","group":{"id":2,"description":"Proof of identity","enabled":true,"name":"Proof of identity","priority":1,"resources":[{"key":"caption","locale":"ru_RU","value":"\u0423\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435 \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"},{"key":"description","locale":"ru_RU","value":"\u0423\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435 \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"}],"type":"one"},"maxFiles":3,"name":"Driving license","priority":2,"resources":[]},{"id":4,"description":" Utility bill","enabled":true,"example":"","group":{"id":3,"description":"Proof of address","enabled":true,"name":"Proof of address","priority":2,"resources":[{"key":"caption","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"},{"key":"description","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"}],"type":"one"},"maxFiles":3,"name":"Utility bill","priority":3,"resources":[]},{"id":5,"description":"Tax Invoice ","enabled":true,"example":"","group":{"id":3,"description":"Proof of address","enabled":true,"name":"Proof of address","priority":2,"resources":[{"key":"caption","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"},{"key":"description","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"}],"type":"one"},"maxFiles":3,"name":"Tax Invoice","priority":4,"resources":[]},{"id":6,"description":" Certificate of registration","enabled":false,"example":"","group":{"id":3,"description":"Proof of address","enabled":true,"name":"Proof of address","priority":2,"resources":[{"key":"caption","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"},{"key":"description","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0430\u0434\u0440\u0435\u0441\u0430"}],"type":"one"},"maxFiles":3,"name":"Certificate of registration","priority":5,"resources":[]},{"id":7,"description":"  selfie","enabled":true,"example":"","group":{"id":4,"description":"Selfie with proof of identity","enabled":true,"name":"Selfie with proof of identity","priority":3,"resources":[{"key":"caption","locale":"ru_RU","value":"\u0421\u0435\u043b\u0444\u0438 \u0441 \u0443\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435\u043c \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"},{"key":"description","locale":"ru_RU","value":"\u0421\u0435\u043b\u0444\u0438 \u0441 \u0443\u0434\u043e\u0441\u0442\u043e\u0432\u0435\u0440\u0435\u043d\u0438\u0435\u043c \u043b\u0438\u0447\u043d\u043e\u0441\u0442\u0438"}],"type":"one"},"maxFiles":3,"name":"Selfie with proof of identity","priority":6,"resources":[]},{"id":8,"description":" Deposit Receipt","enabled":true,"example":"","group":{"id":5,"description":"Proof of deposit","enabled":true,"name":"Proof of deposit","priority":4,"resources":[{"key":"caption","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0434\u0435\u043f\u043e\u0437\u0438\u0442\u0430"},{"key":"description","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0434\u0435\u043f\u043e\u0437\u0438\u0442\u0430"}],"type":"one"},"maxFiles":3,"name":"Deposit Receipt","priority":7,"resources":[]},{"id":9,"description":" Bank Slip","enabled":true,"example":"","group":{"id":6,"description":"Proof of bank transfer","enabled":true,"name":"Proof of bank transfer","priority":5,"resources":[{"key":"caption","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0431\u0430\u043d\u043a\u043e\u0432\u0441\u043a\u043e\u0433\u043e \u043f\u0435\u0440\u0435\u0432\u043e\u0434\u0430"},{"key":"description","locale":"ru_RU","value":"\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0431\u0430\u043d\u043a\u043e\u0432\u0441\u043a\u043e\u0433\u043e \u043f\u0435\u0440\u0435\u0432\u043e\u0434\u0430"}],"type":"one"},"maxFiles":3,"name":"Bank Slip","priority":8,"resources":[]}]}

    public String get() throws Exception {
        EncodeParameters encodeParameters = new EncodeParameters();
        String url = getUrl() + "/api/v2/documents/types";
        Map<String, String> params = new HashMap<>();
        params.put("limit", "10");
        // params.put("offset", "0");
        //params.put("sort_order", "desc");
        //params.put("sort_by", "createTime");
        //params.put("filter[enabled]", "true");
        String urlFinal = url + "?" + encodeParameters.encode(params);

        HttpURLConnection connection = (HttpURLConnection) new URL(urlFinal).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed: Http error code:" + connection.getResponseCode());
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Documents type: ");
        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
            stringBuilder.append(output);

        }

        connection.disconnect();
        return stringBuilder.toString();

    }


}
