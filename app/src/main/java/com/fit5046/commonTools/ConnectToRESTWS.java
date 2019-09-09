package com.fit5046.commonTools;

import android.util.Log;

import com.fit5046.RESTfulWSEntities.Appuser;
import com.fit5046.RESTfulWSEntities.Report;
import com.fit5046.RESTfulWSEntities.Usercredential;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConnectToRESTWS {

    //BaseURL(localIP+ProjectName+webresource)
    private static final String BASE_URL = "http://10.0.2.2:8080/CalorieTrackerApp/webresources/";

    public static String findUserByid(int userid){
        final String methodPath = "com.fit5046.entity.appuser/" + userid;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // Confirm Login
    public static String findUserByUsernameAndPwd(String username, String password){
        final String methodPath = "com.fit5046.entity.usercredential/findByUsernameAndPwd/" + username + "/" + password;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    //Get food from localDB
    public static String findFoodByCategory(String category) {
        final String methodPath = "com.fit5046.entity.food/findByCategory/" + category;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    //Get food from localDB
    public static String findFoodByFoodName(String foodName) {
        final String methodPath = "com.fit5046.entity.food/findByFoodname/" + foodName;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }


    // Create new report
    public static void createReport(Report report){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "com.fit5046.entity.report/";
        try{
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson = gson.toJson(report);
            url = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringReportJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    // Update new report
    public static void updateReport(Long reportid, Report report){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "com.fit5046.entity.report/" + reportid;
        try{
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson = gson.toJson(report);
            url = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringReportJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    // check username is unique during sign up
    public static String findUserByUsername(String username){
        final String methodPath = "com.fit5046.entity.usercredential/findByUsername/" + username + "/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // check email is unique during sign up
    public static String findUserByEmail(String email){
        final String methodPath = "com.fit5046.entity.appuser/findByEmail/" + email + "/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // Sign up a new User
    public static void createUser(Appuser newUser){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "com.fit5046.entity.appuser/";
        try{
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson = gson.toJson(newUser);
            url = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringReportJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    // Sign up a new user credential
    public static void createUserCredential(Usercredential usercredential){
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "com.fit5046.entity.usercredential/";
        try{
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String stringReportJson = gson.toJson(usercredential);
            url = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringReportJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    // get all user count number
    public static String userCount(){
        final String methodPath = "com.fit5046.entity.appuser/count";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // get all userCredential count number
    public static String userCredentialCount(){
        final String methodPath = "com.fit5046.entity.usercredential/count?";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // check user has exist report record
    public static String findRecordByUseridAndDate(long userid, String date){
        final String methodPath = "com.fit5046.entity.report/findByUseridAndDate/" + String.valueOf(userid) + "/" + date;
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    // get all userCredential count number
    public static String reportCount(){
        final String methodPath = "com.fit5046.entity.report/count?";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }




}
