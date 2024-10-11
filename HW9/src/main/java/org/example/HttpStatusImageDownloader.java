package org.example;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpStatusImageDownloader {

    private HttpStatusChecker checker;

    public void downloadStatusImage(int code) throws Exception {
        checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(code);
        URL url = new URL(imageUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int status = connection.getResponseCode();
        if (status == 404) {
            throw new Exception(imageUrl + " not found");
        }

        try (InputStream inputStream = connection.getInputStream()) {
            Path imagePath = Paths.get(code + ".jpg");

            Files.copy(inputStream, imagePath);
            System.out.println(imagePath.toAbsolutePath());

        }catch (Exception e) {
           throw new Exception(imageUrl + " not found");
        }

    }
}
