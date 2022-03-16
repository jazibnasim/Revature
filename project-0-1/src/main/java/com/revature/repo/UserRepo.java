package com.revature.repo;

import com.revature.model.User;
import com.revature.util.ConnectionBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo {

    private static final Logger logger = LogManager.getLogger(ConnectionBridge.class);

    public User createUser(String username, String password, String firstName, String lastName){
        User user = null;


        try (Connection connection = ConnectionBridge.connect()) {
            String sql = "insert into users(username, password, firstname, lastname) values (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);


            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);




            // this
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname")
                );
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        }
        return user;
    }

    public boolean validateUserName(String username, String password){
        User user = null;



        try (Connection connection = ConnectionBridge.connect()) {
            String sql = "select username,password from users where username=?";
            PreparedStatement stmt = connection.prepareStatement(sql);


            stmt.setString(1, username);
            stmt.setString(2, password);

            // this
            ResultSet rs = stmt.executeQuery();
            String orgUname = "", orPass = "";

            if(rs.next()){

                while (rs.next()) {
                    orgUname = rs.getString("userName");
                    orPass = rs.getString("password");
                }
                if (orPass.equals(password)) {
                    //do something
//                    user = new User(
//                            rs.getString("username"),
//                            rs.getString("password"),
//                            rs.getString("firstname"),
//                            rs.getString("lastname")
//                    );
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));

                    return true;
                } else {
                    System.out.println("Incorrect Username or Password. Please try again!");
                }



            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        }
        return false;
    }


}
