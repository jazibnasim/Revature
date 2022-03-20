package com.revature.repo;

import com.revature.model.User;
import com.revature.util.ConnectionBridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountRepo {

    /**-------Work-in-progress to modularize-------*/

    UserRepo userRepo = new UserRepo();

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
                userRepo.currentUser.setBalance(orBal);
            }
        }
        catch (Exception e) {
        }
    }
}
