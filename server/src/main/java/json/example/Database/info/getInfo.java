package json.example.Database.info;

import json.example.Database.SetConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class getInfo {

    private static Statement statement = SetConnection.getConnection();

    public static String getInfoAboutUser(String username) {
        JSONArray historyArr = new JSONArray();
        JSONObject object = new JSONObject();

        for(String info: getInfoAboutUserFromUsername(username).keySet()){
            object.put(info, getInfoAboutUserFromUsername(username).get(info));
        }

        object.put("score", getScore(username));

        for(String history: getHistory(username)){
            historyArr.put(history);
        }

        object.put("history", historyArr);

        return object.toString();
    }

    public static String getInfoAboutUser(long phone){
        JSONArray historyArr = new JSONArray();
        JSONObject object = new JSONObject();

        String username = getInfoAboutUserFromPhone(phone).get("username");

        for(String info: getInfoAboutUserFromUsername(username).keySet()){
            object.put(info, getInfoAboutUserFromUsername(username).get(info));
        }

        object.put("score", getScore(username));

        for(String history: getHistory(username)){
            historyArr.put(history);
        }

        object.put("history", historyArr);

        return object.toString();
    }


    private static String[] getHistory(String username){
        String[] arrayForHistory = new String[10];
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM history WHERE username = '"+username+"' ORDER BY id DESC LIMIT 10");
            int i = 0;
            while(resultSet.next()){
                int type_of_operation = resultSet.getInt("type_of_operation");
                long summa = resultSet.getLong("summa");

                String history = type_of_operation + ":" + summa;
                arrayForHistory[i++] = history;
            }
        } catch (SQLException e){
            System.out.println("Ошибка в getInfo в методе getHistory");
            e.printStackTrace();
        }

        return  arrayForHistory;
    }

    private static long getScore(String username){
        long score = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM score WHERE username = '"+username+"'");
            while(resultSet.next()){
                score = resultSet.getLong("score");
            }
        }catch (SQLException e){
            System.out.println("Ошибка в getInfo в методе getScore");
            e.printStackTrace();
        }

        return score;
    }

    private static Map<String, String> getInfoAboutUserFromUsername(String username){
        Map<String, String> mapForUser = new HashMap<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_database WHERE username = '"+username+"'");
            while(resultSet.next()){
                String name = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String phoneNumber = ""+resultSet.getLong("phone");
                String userName = resultSet.getString("username");
                mapForUser.put("firstname", name);
                mapForUser.put("surname", surname);
                mapForUser.put("phone", phoneNumber);
                mapForUser.put("username", userName);
            }
        }catch (SQLException e){
            System.out.println("Ошибка в getInfo в методе getInfoAboutUserFromUsername");
            e.printStackTrace();
        }

        return mapForUser;
    }

    public static Map<String, String> getInfoAboutUserFromPhone(long phone){
        Map<String, String> mapForUser = new HashMap<>();

        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_database WHERE phone = "+phone);
            while(resultSet.next()){
                String name = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String phoneNumber = ""+resultSet.getLong("phone");
                mapForUser.put("firstname", name);
                mapForUser.put("surname", surname);
                mapForUser.put("username", username);
                mapForUser.put("phone", phoneNumber);
            }
        }catch (SQLException e){
            System.out.println("Ошибка в getInfo в методе getInfoAboutUserFromPhone");
            e.printStackTrace();
        }

        return mapForUser;
    }


}
