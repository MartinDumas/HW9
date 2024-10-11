package org.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {

    public String getStatusImage(int code) throws Exception {

        String urlString = "https://http.cat/" + code + ".jpg";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new Exception("There is no HTTP status " + code);
        }
        return urlString;

    }

}
