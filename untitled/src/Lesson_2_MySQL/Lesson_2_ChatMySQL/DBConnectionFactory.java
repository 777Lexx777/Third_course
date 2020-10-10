package Lesson_2_MySQL.Lesson_2_ChatMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/geekbrains?serverTimezone=Europe/Moscow",
                    "root", "root");
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong during establishing DB connection");
        }
    }
}
