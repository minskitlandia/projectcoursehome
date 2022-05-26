package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DBNAME = "project?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static final DBConnection INSTANCE = new DBConnection();

    private DBConnection() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DataBase not connection...");
        }
        return connection;
    }
}
