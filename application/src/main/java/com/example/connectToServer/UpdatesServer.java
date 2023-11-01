package com.example.connectToServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class UpdatesServer {
    private static final String URL = "http://localhost:8080/getApi/update?";
    public static int singUp(String username, String firstname, String surname, String phone, String password){
        int state = connect(URL + "type=new-user&username=" + username + "&firstname="+firstname+"&surname="+surname+"&phone="+phone+"&password="+password);
        return state;
    }

    public static int deleteAccountFromUsername(String username){
        int state = connect(URL + "type=delete-user&username="+username);
        return state;
    }

    public static int deleteAccountFromPhone(String phone){
        int state = connect(URL + "type=delete-user&phone="+phone);
        return state;
    }

    public static int plusScore(String username, long sum){
        int state = connect(URL + "type=update-score&update-type=plus&username="+username+"&summa="+sum);
        return state;
    }

    public static int minusScore(String username, long sum){
        int state = connect(URL + "type=update-score&update-type=minus&username="+username+"&summa="+sum);
        return state;
    }

    public static int toZeroScore(String username){
        int state = connect(URL + "type=update-score&update-type=zero&username="+username);
        return state;
    }

    public static int reName(String username, String newName){
        int state = connect(URL + "type=update-user&username="+username+"&new-name="+newName);
        return state;
    }

    public static int reSurname(String username, String newSurname){
        int state = connect(URL + "type=update-user&username="+username+"&new-surname="+newSurname);
        return state;
    }

    public static int clearHistory(String username){
        int state = connect(URL + "type=update-history&history-type=clear-history&username="+username);
        return state;
    }

    public static int deleteLastOperation(String username){
        return connect(URL + "type=update-history&history-type=dlt-lst-oper&username="+username);
    }
    private static int connect(String url) {
        StringBuffer content = new StringBuffer();

        try {
            URL createUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) createUrl.openConnection();

            urlConnection.setRequestMethod("POST");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = buffer.readLine()) != null){
                content.append(line + "\n");
            }

            buffer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        JSONObject object = new JSONObject(content.toString());

        return object.getInt("state");
    }
}
