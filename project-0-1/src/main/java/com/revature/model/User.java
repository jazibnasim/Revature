package com.revature.model;

public class User {

    private String userName = "";
    private String userPassword = "";
    private Double balance = 0.00;
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String username, String password, Double balance) {

        this.id = id;
        this.userName = username;
        this.userPassword = password;
        this.balance = balance;

    }

    public User(String username, String password) {

        this.userName = username;
        this.userPassword = password;
        this.balance = balance;

    }



    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
                '}';
    }
}
