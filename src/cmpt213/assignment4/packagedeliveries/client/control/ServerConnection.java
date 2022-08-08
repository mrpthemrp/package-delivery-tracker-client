package cmpt213.assignment4.packagedeliveries.client.control;

import cmpt213.assignment4.packagedeliveries.client.view.PackageDeliveryGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This class establishes connection to the server and has methods to
 * handle requests.
 *
 * @author Deborah Wang
 * @link <a href="https://www.digitalocean.com/community/tutorials/java-httpurlconnection-example-java-http-request-get-post">...</a> HTTP Reference
 */
public class ServerConnection {
    public final static String GET = "GET";
    public final static String POST = "POST";

    /**
     * Constructor for this class. On constructor call
     * starts connection with server.
     */
    public ServerConnection() {
        System.out.println(getMessage("ping"));
    }

    /**
     * Method that allows client to sent GET messages to the server.
     *
     * @param command The GET command that will be sent.
     * @return Returns a String version of what the server sent back.
     */
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

    /**
     * Method that allows client to send a POST message to server.
     *
     * @param command   POST command to be sent.
     * @param postType  Type of Post message to be sent, helps with client logic.
     * @param pkg       PackageBase to be sent, in String JSON format.
     * @param pkgIndex  Index of the package in the current state's list.
     * @param newStatus The new delivery status of the package, false if REMOVE or ADD.
     * @return Returns a String version of the updated arraylist from the server.
     * @link <a href="https://www.baeldung.com/httpurlconnection-post">...</a> Reference for HTTP
     */
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
                    String stringContents = "[" + pkgIndex + "," + PackageDeliveryGUI.currentState.toString() + "]";
                    input = stringContents.getBytes(StandardCharsets.UTF_8);

                } else if (postType == PackageDeliveryControl.DELIVERY_STATUS) {
                    String stringContents = "[" + pkgIndex + "," + newStatus + "," + PackageDeliveryGUI.currentState.toString() + "]";
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

    /**
     * Helper method with parsing in what the server sends.
     *
     * @param server The server to connect to.
     * @return Returns a String version of what the server sent back.
     * @throws IOException Throws this exception when input/output is corrupt.
     */
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
}
