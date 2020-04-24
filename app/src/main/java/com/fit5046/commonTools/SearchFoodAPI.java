package com.fit5046.commonTools;

import com.fit5046.RESTfulWSEntities.Food;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * This class used in searching food label
 * from www.edamam.com
 */
public class SearchFoodAPI {
    // Basic information
    private final static String APP_ID = "Your Own App_ID";
    private final static String API_KEY = "Your Own API_KEY From www.edamam.com";
    private final static String SEARCH_URL = "https://api.edamam.com/api/food-database/parser?ingr=";

    public static String searchFood(String keyword){
        keyword = keyword.replace(" ","%20");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";

        try{
            // According to the api-docs, url should be:
            // 'https://api.edamam.com/api/food-database/parser?ingr={key_word}&app_id={your app_id}&app_key={your app_key}'
            url = new URL(SEARCH_URL+keyword+"&app_id="+APP_ID+"&app_key="+API_KEY);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json"); connection.setRequestProperty("Accept", "application/json");
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

    public static Food getFoodInfoJson(String foodInfo) {
        Food newFood = new Food();
        try {
            JSONObject jsonObject = new JSONObject(foodInfo);
            JSONArray jsonArray = jsonObject.getJSONArray("hints");
            String str = jsonArray.optString(0);
            JSONObject jsonFood = new JSONObject(str);
            JSONObject jsonFood1 = jsonFood.getJSONObject("food");
            JSONObject jsonNutrient = jsonFood1.getJSONObject("nutrients");

            newFood.setFoodName(jsonFood1.getString("label"));
            newFood.setCalorieAmount(jsonNutrient.getInt("ENERC_KCAL"));
            newFood.setFatAmount(jsonNutrient.getInt("FAT"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFood;
    }


}
