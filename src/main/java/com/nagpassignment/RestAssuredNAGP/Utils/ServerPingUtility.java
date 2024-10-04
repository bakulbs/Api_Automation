package com.nagpassignment.RestAssuredNAGP.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerPingUtility {

    public static boolean isServerUp(String serverUrl) {
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            // Set a reasonable timeout for the ping request
            connection.setConnectTimeout(5000); // 5 seconds

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false; // An exception indicates that the server is not reachable
        }
    }
}
