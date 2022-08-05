package cmpt213.assignment4.packagedeliveries.client.control;

import cmpt213.assignment4.packagedeliveries.client.view.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Server {
    //GET POST REQUESTS
    private final static String BASE_URL = "http://localhost:8080/";

    //other
    private URL url;
    private HttpURLConnection server;

    public Server() {
        System.out.println(BASE_URL);
        startServer();
    }

    private void startServer() {
        try {
            this.url = new URL(BASE_URL + "ping");
            this.server = (HttpURLConnection) url.openConnection();
            this.server.setRequestMethod("GET");
            this.server.setRequestProperty("Content-Type", "application/json");
            int responseCode = server.getResponseCode();

            System.out.println("GET Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String inputLine;

                StringBuffer response = new StringBuffer();

                while ((inputLine = input.readLine()) != null) {
                    response.append(inputLine);
                }
                input.close();
                System.out.println("GET Message: " + response.toString());
            } else {
                System.out.println("not ok");
            }

        } catch (IOException ie) {
            //do something
        }
    }

}
