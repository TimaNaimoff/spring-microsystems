package edu.javacourse.tomcat.connection;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionDb {
    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/al_db",
                "postgres",
                "TERMIT006WIN"
        );
    }
}
