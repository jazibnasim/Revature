package com.revature.util;

import com.revature.model.User;

import java.sql.*;
import java.util.Locale;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;




public class ConnectionBridge {

    private static final Logger logger = LogManager.getLogger(ConnectionBridge.class);

    // "jdbc:<dialect>://<host name>:<port>/<database name>?currentSchema=<schema name>"
    private static final String url =
            "jdbc:postgresql://java-react.cewgx1k1p4rc.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=project0";


    private static final String username = "postgres";
    private static final String password = "sJits7549!";

    public ConnectionBridge() {
    }


    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    Statement statement;

    {
        try {
            statement = connect().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ResultSet resultSet;

    {
        try {
            resultSet = statement.executeQuery("select * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void myTest() throws SQLException {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("firstname"));
        }
    }
}






