package json.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetConnection {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1111";
    private static final String URL = "jdbc:postgresql://localhost:5432/dbforbudget";
    private static Connection connection;

    public static Statement getConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(connection!=null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            return connection.createStatement();
        }catch (SQLException e){
            return null;
        }

    }
}
