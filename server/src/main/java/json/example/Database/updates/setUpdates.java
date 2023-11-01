package json.example.Database.updates;

import json.example.Database.SetConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class setUpdates {

    private static Statement statement = SetConnection.getConnection();
    private static Statement statement2 = SetConnection.getConnection();

    public static void setSingUp(String username, String firstname, String surname, String phone, String password){
        String query = "INSERT INTO users_database(username, firstname, surname, phone, password_user) VALUES ('"+username+"', '"+firstname+"', '"+surname+"', "+phone+", '"+password+"')";
        toStatement(query);
        query = "INSERT INTO score(username, score) VALUES ('"+username+"', 0)";
        toStatement(query);
    }

    public static void deleteStringFromUsers(String username){
        String query = "DELETE FROM users_database WHERE username = '"+username+"'";
        toStatementUpdate(query);
        query = "DELETE FROM score WHERE username = '"+username+"'";
        toStatementUpdate(query);
        query = "DELETE FROM history WHERE username = '"+username+"'";
        toStatementUpdate(query);
    }

    public static void updateScorePlus(String username, long sumScore) throws SQLException{
        long score = getScore(username) + sumScore;
        String query = "UPDATE score SET score = "+score+" WHERE username = '"+username+"'";
        toStatement(query);
    }

    public static void updateScoreMinus(String username, long sumScore) throws SQLException{
        long score = getScore(username) - sumScore;
        String query = "UPDATE score SET score = "+score+" WHERE username = '"+username+"'";
        toStatement(query);
    }

    public static void addHistoryPlus(String username, long sum){
        String query = "INSERT INTO history(username, score_history, summa, type_of_operation) VALUES ('"+username+"', 'Пополнение счёта', "+sum+", 1)";
        toStatement(query);
    }

    public static void addHistoryMinus(String username, long sum){
        String query = "INSERT INTO history(username, score_history, summa, type_of_operation) VALUES ('"+username+"', 'Снятие со счёта', "+sum+", 0)";
        toStatement(query);
    }

    public static void toReName(String newName, String username){
        String query = "UPDATE users_database SET firstname = '"+newName+"' WHERE username = '"+username+"'";
        toStatement(query);
    }
    public static void toReSurname(String newSurname, String username){
        String query = "UPDATE users_database SET surname = '"+newSurname+"' WHERE username = '"+username+"'";
        toStatement(query);

    }

    public static void clearHistory(String username){
        String query = "DELETE FROM history WHERE username = '"+username+"'";
        toStatementUpdate(query);
    }

    public static void toZeroScore(String username){
        String query = "UPDATE score SET score = 0 WHERE username = '"+username+"'";
        toStatementUpdate(query);
    }

    public static void deleteLastOperation(String username) throws SQLException{
        String query = "SELECT * FROM history WHERE id = (SELECT MAX(id) FROM history WHERE username = '"+username+"')";
        int lastOperationId = 0;
        ResultSet resultSet = statement2.executeQuery(query);
        while(resultSet.next()){
            lastOperationId = resultSet.getInt("id");

            long operation = resultSet.getLong("summa");

            if(resultSet.getInt("type_of_operation") == 0){
                updateScorePlus(username, operation);
            }
            else{
                updateScoreMinus(username, operation);
            }
        }

        query = "DELETE FROM history WHERE id = "+lastOperationId;
        toStatement(query);
    }

    private static ResultSet getResult(String query){
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static long getScore(String username) throws SQLException{
        String query = "SELECT score FROM score WHERE username = '"+username+"'";
        ResultSet resultSet = getResult(query);
        while(resultSet.next()){
            long score = resultSet.getLong("score");
            return score;
        }
        return 0;
    }

    private static void toStatement(String sql){
        try{
            statement.execute(sql);
        }catch (SQLException e){
            System.out.println("Проблема в setUpdates в методе toStatement");
            e.printStackTrace();
        }
    }

    private static void toStatementUpdate(String sql){
        try{
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("Ошибка в setUpdates в методе toStatementUpdate");
        }
    }

}
