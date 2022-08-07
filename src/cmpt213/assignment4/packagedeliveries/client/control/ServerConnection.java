package cmpt213.assignment4.packagedeliveries.client.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServerConnection {
    public final static String GET = "GET";
    public final static String POST = "POST";

    public ServerConnection() {
        System.out.println(getMessage("ping"));
    }

    public String getMessage(String command) {
        try {
            String BASE_URL = "http://localhost:8080/";
            URL url = new URL(BASE_URL + command);
            HttpURLConnection server = (HttpURLConnection) url.openConnection();
            server.setRequestMethod(GET);
            server.setRequestProperty("Content-Type", "application/json");
            int responseCode = server.getResponseCode();

            System.out.println(GET + " Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return parseServerStream(server);
            } else {
                System.out.println("Error");
            }

        } catch (IOException ie) {
            //do something
        }
        return null;
    }

    private String parseServerStream(HttpURLConnection server) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String inputLine;

        StringBuilder response = new StringBuilder();

        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        input.close();
        return response.toString();
    }

    public String postMessage(String command, int postType, String pkg, int pkgIndex, boolean newStatus) {
        try {
            String BASE_URL = "http://localhost:8080/";
            URL url = new URL(BASE_URL + command);
            HttpURLConnection server = (HttpURLConnection) url.openConnection();
            server.setRequestMethod(POST);
            server.setRequestProperty("Content-Type", "application/json");
            server.setRequestProperty("Accept", "application/json");
            server.setDoOutput(true);

            byte[] input = null;
            try (OutputStream os = server.getOutputStream()) {
                if (postType == PackageDeliveryControl.ADD) {
                    input = pkg.getBytes(StandardCharsets.UTF_8);
                } else if (postType == PackageDeliveryControl.REMOVE) {
                    input = Integer.toString(pkgIndex).getBytes(StandardCharsets.UTF_8);

                } else if (postType == PackageDeliveryControl.DELIVERY_STATUS) {
                    String stringContents = "[" + pkgIndex + "," + newStatus + "]";
                    input = stringContents.getBytes(StandardCharsets.UTF_8);
                }
                assert input != null;
                os.write(input, 0, input.length);
            }


            int responseCode = server.getResponseCode();

            System.out.println(POST + " Response Code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                return parseServerStream(server);
            } else {
                System.out.println("Error");
            }

        } catch (IOException ie) {
            //do something
        }
        return null;
    }
}
