package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {

    private HttpStatusChecker checker = new HttpStatusChecker();
    private HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    public void askStatus() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter HTTP status code:");

            if (sc.hasNextInt()) {
                int status = sc.nextInt();

                try {
                    String imageUrl = checker.getStatusImage(status);
                    downloader.downloadStatusImage(status);
                    System.out.println("Image downloaded from: " + imageUrl);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Please enter a valid HTTP status code.");
                sc.next();
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
