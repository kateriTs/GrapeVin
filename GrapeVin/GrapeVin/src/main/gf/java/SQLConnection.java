package gf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/winedb?user=root&password=Foolcavetown201:3306/winedb";
            String username = "root";
            String password = "putyourpassword";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }
}
