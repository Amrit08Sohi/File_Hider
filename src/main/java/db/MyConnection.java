package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection = null;

    public static Connection getConnection()  {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fileHiderDb?useSSL=false","root","Tottaiitbtiip123@");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to DB");
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
