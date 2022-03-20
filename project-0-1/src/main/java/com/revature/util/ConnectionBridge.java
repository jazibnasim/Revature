package com.revature.util;

import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ConnectionBridge {

    //Logging Issues to Text File
    private static final Logger logger = LogManager.getLogger(ConnectionBridge.class);

    // "jdbc:<dialect>://<host name>:<port>/<database name>?currentSchema=<schema name>"

    //These variables will be inputted in connect() method as parameters
    private static final String url =
            "jdbc:postgresql://java-react.cewgx1k1p4rc.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=project0";

    private static final String username = "postgres";
    private static final String password = "sJits7549!";

    //Method created to connect JDBC to SQL Database using variables declared above ^
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);  //Built in method call on connection
            //System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}






