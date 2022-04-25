package com.the_losst.curs;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class include_bd {
    private Connection connection = null;
    private Statement statement = null;
    private static include_bd instance = null;
    public include_bd(){
        instance = this;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:login.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.execute("CREATE TABLE IF NOT EXISTS userData ( login TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL, phoneNum TEXT NOT NULL );");
        }
           catch(SQLException e)
           {
                System.err.println("Все пошло по пизде");
           }
//
//        try {
//            md = MessageDigest.getInstance("MD5");
//        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
    }

    private boolean loginCorrect(String login){
        //TODO:  ХУЙ//
        return true;
    }

    private boolean userExist(String login){
        try {
            System.out.println("[AL.LoginDB] Seek started.");
            String request = "SELECT COUNT (login) FROM userData WHERE nick = \"" + login + "\";";
            if (statement.executeQuery(request).getInt(1) == 1) {

                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Что то пошло по пизде .-.");
            return false;
        }
    }

    public int registerUser(String login, String password, String phoneNum){
        if(login!=null && password!=null && phoneNum!=null && loginCorrect(login)){
            if (!userExist(login)){
                try {
                    statement.executeUpdate("INSERT INTO userData VALUES(\"" + login + "\", \"" + password + "\", \"" + phoneNum + "\")");
                    return 0;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return -3;
                }
            }
            else return -2;
        }
        else return -1;
    }

    private boolean checkPass(String login,String password){
        String request = "SELECT (password) FROM userData WHERE login = \"" + login + "\";";
        try {
            if (statement.executeQuery(request).getString(1).equals(password)) {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Что то пошло по пизде .-.");
        }
        return false;
    }

    public int loginUser(String login, String password){
        if(login!=null && password!=null && loginCorrect(login)){
            if (userExist(login)){
                if (checkPass(login,password)){
                    return 0;
                }
                else return -3;
            }
            else return -2;
        }
        else return -1;
    }

    public include_bd getInstance() {
        return instance;
    }

}
