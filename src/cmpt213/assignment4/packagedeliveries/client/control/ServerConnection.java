package cmpt213.assignment4.packagedeliveries.client.control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConnection {
    public final static String GET_ALL = "listAll";
    public final static String GET_OVERDUE = "listOverduePackage";
    public final static String GET_UPCOMING = "listUpcomingPackage";
    public final static String POST_ADD_PACKAGE = "addPackage";
    public final static String POST_REMOVE_PACKAGE = "removePackage";
    public final static String POST_MARK_DELIVERED = "markPackageAsDelivered";
    public final static String EXIT = "exit";

    private final Gson gson;
    public ServerConnection(Gson gson) {
        this.gson = gson;
        System.out.println(sendMessage("ping","GET", HttpURLConnection.HTTP_OK));
    }

    public String sendMessage (String command, String requestType, int connectionResult){
        try {
            String BASE_URL = "http://localhost:8080/";
            URL url = new URL(BASE_URL + command);
            HttpURLConnection server = (HttpURLConnection) url.openConnection();
            server.setRequestMethod(requestType);
            server.setRequestProperty("Content-Type", "application/json");
            int responseCode = server.getResponseCode();

            System.out.println(requestType+" Response Code: " + responseCode);

            if (responseCode == connectionResult) {
                BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String inputLine;

                StringBuilder response = new StringBuilder();

                while ((inputLine = input.readLine()) != null) {
                    response.append(inputLine);
                }
                input.close();
                return response.toString();
            } else {
                System.out.println("Error");
            }

        } catch (IOException ie) {
            //do something
        }
        return null;
    }
}
