package org.klimov;

import java.util.Map;

public class EncodeParameters {
    public String encode(Map<String, String> params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (
                Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        return result.toString();
    }

}
