package com.revature.model;
import com.revature.util.ConnectionBridge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User {




    private String userName = "";
    private String userPassword = "";
    private String firstName = "";
    private String lastName = "";



    public User(String username, String password, String firstName, String lastName) {

        this.userName = username;
        this.userPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
