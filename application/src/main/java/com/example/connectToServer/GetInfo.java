package com.example.connectToServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetInfo {
    public static String getJson(String username){
        StringBuffer content = new StringBuffer();

        String urlString = "http://localhost:8080/getApi/getInfo?username="+username;

        try{
            URL url = new URL(urlString);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferrReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferrReader.readLine()) != null){
                content.append(line + "\n");
            }

            bufferrReader.close();
        }catch(IOException e){
            e.printStackTrace();;
        }

        return content.toString();
    }

    public static String getJsonFromPhone(String phone){
        StringBuffer content = new StringBuffer();
        
        String urlString = "http://localhost:8080/getApi/getInfo?phone="+phone;

        try {
            URL url = new URL(urlString);
            URLConnection urlConn = url.openConnection();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line=buffer.readLine()) != null){
                content.append(line +"\n");
            }
            buffer.close();
        } catch (IOException e) {
            // TODO: handle exception
        }

        return content.toString();
    }

    public static String getFirstname(String username){
        JSONObject object = getInfoFromJson(username);
        return object.getString("firstname");
    }

    public static String getFirstnameFromPhone(String phone){
        JSONObject object = getInfoFromJsonAboutPhone(phone);
        return object.getString("firstname");
    }

    public static String getSurname(String username){
        JSONObject object = getInfoFromJson(username);
        return object.getString("surname");
    }

    public static String getSurnameFromPhone(String phone){
        JSONObject object = getInfoFromJsonAboutPhone(phone);
        return object.getString("surname");
    }

    public static long getScoreFromPhone(String phone){
        JSONObject object = getInfoFromJsonAboutPhone(phone);
        return object.getLong("score");
    }

    public static long getScore(String username){
        JSONObject object = getInfoFromJson(username);
        return object.getLong("score");
    }

    public static String[] getHistory(String username){
        JSONObject object = getInfoFromJson(username);
        JSONArray array = object.getJSONArray("history");
        String[] list = new String[10];

        for (int i = 0; i < array.length(); i++) {
            Object operation = array.get(i);
            if(operation == null){
                operation = "";
            }
            list[i] = ""+operation;
        }

        return list;
    }

    public static String[] getHistoryFromPhone(String phone){
        JSONObject object = getInfoFromJsonAboutPhone(phone);
        JSONArray array = object.getJSONArray("history");
        String[] list = new String[10];

        for (int i = 0; i < array.length(); i++) {
            Object operation = array.get(i);
            if(operation == null){
                operation = "";
            }
            list[i] = ""+operation;
        }

        return list;
    }

    public static boolean checkPasswordFromUsername(String username, String password){
        JSONObject obj = getInfoFromJson(username);
        return obj.getString("password").equals(password);
    }

    public static boolean checkPasswordFromPhone(String phone, String password){
        JSONObject obj = getInfoFromJsonAboutPhone(phone);
        return obj.getString("password").equals(password);
    }

    public static boolean isUsername(String username){
        JSONObject obj = getInfoFromJson(username);
        return obj.getBoolean("isUsername");
    }

    public static String getUsernameFromPhone(String phone){
        JSONObject object = getInfoFromJsonAboutPhone(phone);
        return object.getString("username");
    }

    public static boolean checkLoginPhone(String login){
        try{
            Long.parseLong(login);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isPhone(String phone){
        JSONObject obj = getInfoFromJsonAboutPhone(phone);
        return obj.getBoolean("isPhone");
    }

    private static JSONObject getInfoFromJson(String username){
        JSONObject object = new JSONObject(getJson(username)); 
        return object;
    }

    private static JSONObject getInfoFromJsonAboutPhone(String phone){
        JSONObject object = new JSONObject(getJsonFromPhone(phone));
        return object;
    }
}
