package com.example.tennis_tracker_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {

    String DB_URL = "jdbc:mysql://10.0.2.2:8889/Tennis_Tracker_Project";
    String USER = "root";
    String PASSWORD = "root";

    public Connection ConnectionToDataBase(){
        String msg = "";
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if (connection == null) {
                msg = "Connection goes wrong, there is no one";
            } else {

                System.out.println("The query was successfully done");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }


}
