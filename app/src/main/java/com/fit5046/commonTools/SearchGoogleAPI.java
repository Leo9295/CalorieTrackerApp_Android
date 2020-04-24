package com.fit5046.commonTools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SearchGoogleAPI {

    private static final String SEARCH_URL = "https://www.googleapis.com/customsearch/v1?";
    private static final String API_KEY = "Your Own API_KEY From Google Account";
    private static final String SEARCH_ID_cx = "012983880789318303979:rfasjccwhju";

    public static String searchGoogleAPI(String keyword) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter = "&num=1";
        try {
            url = new URL(SEARCH_URL + "key=" + API_KEY + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + query_parameter);
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

    public static String getGoogleResult(String keyword) {
        String result = searchGoogleAPI(keyword);
        String snippet = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null && jsonArray.length() > 0) {
                snippet = jsonArray.getJSONObject(0).getString("snippet");
                snippet = snippet.substring(0,snippet.indexOf(".") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            snippet = "NO INFO FOUND";
        }
        return snippet;
    }

    public static String searchPic(String keyword) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter = "&num=1&searchType=image";
        try {
            url = new URL(SEARCH_URL + "key=" + API_KEY + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + query_parameter);
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

    public static String getPicResult(String keyword) {
        String str = searchPic(keyword);
        try {
            JSONObject obj = new JSONObject(str);
            JSONArray array = obj.getJSONArray("items");
            JSONObject obj1 = array.getJSONObject(0);
            JSONObject target = obj1.getJSONObject("image");
            return target.getString("thumbnailLink");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
