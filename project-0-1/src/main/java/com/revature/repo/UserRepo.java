package com.revature.repo;

import com.revature.model.User;
import com.revature.util.ConnectionBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class UserRepo {

    private static final Logger logger = LogManager.getLogger(ConnectionBridge.class);
    public User currentUser;

    //  This method populates a new row in the SQL database
    //  under each specific column with user-inputted data from register() method

    public User createUser(String username, String password){
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
                        resultSet.getString("password")

                );
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        }
        return user;
    }

    //This method approves the user to login based on username/password combinations present in database
    public boolean checkLogin(String username, String password){

        try (Connection connection = ConnectionBridge.connect()) {

            String sql = "select id, username,password,balance from users where userName=?"; //SQL-query
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            //Pull info from columns in table and assign to variables
            String orgUname = "", orPass = "";
            int orgId = 0;
            Double orBal = 0.00;

            while (rs.next()) {
                orgId = rs.getInt("id");
                orgUname = rs.getString("username");
                orPass = rs.getString("password");
                orBal = rs.getDouble("balance");
            }

            //password validation
            if (orPass.equals(password)) {

                User dbUser = new User(orgId, orgUname, orPass, orBal); //creates an object to store table info from DB
                currentUser = dbUser; //converts object to be used in other packages/classes for convenience
                return true;
            } else {
                System.out.println("Invalid username or password.");
            }
        }
        catch (Exception e) {
        }
        return false;
    }

    //Sync balance from java side to DB side using username as query command
    public void updateBalance(String username, double newBalance) {
        try (Connection connection = ConnectionBridge.connect()) {

            String sql = "update users set balance = ? where username = ?"; //SQL-query
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, newBalance);
            stmt.setString(2, username);

            ResultSet rs = stmt.executeQuery();

            //Pull info from columns in table and assign to variable
            Double orBal;

            while (rs.next()) {
                orBal = rs.getDouble("balance");
                currentUser.setBalance(orBal);
            }
        }
        catch (Exception e) {
        }
    }


}
