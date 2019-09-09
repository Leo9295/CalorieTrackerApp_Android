package com.fit5046.commonTools;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SearchMapAPI {

    private static final String API_KEY = "AIzaSyBJYzmnq0JrOYY9xI1HgVFG5E32eLhZoYM";

    public static String searchPlace(String address) {
        address = address.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        try {
            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + API_KEY);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return textResult;
    }

    public static String showNearParks(Double lat, Double lng) {
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        try {
            url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat + "," + lng + "&type=park&radius=5000&fields=name&key=" + API_KEY);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return textResult;
    }
}
