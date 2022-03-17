package com.revature.repo;

import com.revature.model.User;
import com.revature.util.ConnectionBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;

public class UserRepo {

    private static final Logger logger = LogManager.getLogger(ConnectionBridge.class);
    User user;
    UserRepo userRepo;

    //  This method populates a new row in the SQL database
    //  under each specific column with user-inputted data from register() method

    public User createUser(String username, String password, Double balance){
        User user = null;

        try (Connection connection = ConnectionBridge.connect()) {
            String sql = "insert into users(username, password) values (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();

         if(resultSet.next()){
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        }
        return user;
    }

    public boolean checkLogin(String username, String password)
            throws SQLException {
        System.out.print("dfdF");


        // connect to database usernames and query database
        try (Connection connection = ConnectionBridge.connect()) {


            String sql = "select userName,password,balance from users where userName=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            String orgUname = "", orPass = "";
            Double orBal = 0.00;
            while (rs.next()) {
                orgUname = rs.getString("username");
                orPass = rs.getString("password");
                orBal = rs.getDouble("balance");
            } //end while
            if (orPass.equals(password)) {
                System.out.println(orBal);
                user.setUserName(orgUname);
                user.setPassword(orPass);
                user.setBalance(orBal);
                return true;

              //  rs.close();
            } else {
                //do something
            }
        }//end try
        catch (Exception e) {
        } //end catch
        return false;
    } //end main

    public void updateBalance(String username, double newBalance)
            throws SQLException {


        // connect to database usernames and query database
        try (Connection connection = ConnectionBridge.connect()) {


            String sql = "update users set balance = ? where username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, newBalance);
            stmt.setString(2, username);

            ResultSet rs = stmt.executeQuery();
            Double orBal = 0.00;
            while (rs.next()) {
                orBal = rs.getDouble("balance");
            } //end while
            {
                System.out.println(orBal);
                user.setBalance(orBal);

                // rs.close();
            }
        }//end try
        catch (Exception e) {
        } //end catch
    } //end main


}
