package com.library.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "mysql");
            } catch (SQLException e) {
                // Handle exception
            }
        }
        return connection;
    }
}

