package com.example.tennis_tracker_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseSQL {

    private static final String DB_URL = "jdbc:mysql://localhost:8889/Tennis_Tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void dataBaseConnection(String query){

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if(connection == null){
                System.out.println("Connection goes wrong, there is no one");
            }

            else{
                Statement statement = connection.createStatement();
                statement.execute(query);
                System.out.println("The query was successfully done");

            }

            connection.close();

        }catch(Exception e){

            System.out.println("Exception");
            e.printStackTrace();
        }
    }

}
