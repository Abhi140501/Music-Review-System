package com.musicreview.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    String jdbcURL = "jdbc:mysql://localhost:3306/musicreview";
    String username = "root";
    String password = "Abhi@2001";
    public Connection connection = DriverManager.getConnection(jdbcURL,username,password);

    public Connector() throws SQLException {
    }
}