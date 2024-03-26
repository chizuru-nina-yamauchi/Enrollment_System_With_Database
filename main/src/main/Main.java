package main;

import database_connection.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Get the connection factory instance
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        // Get the database connection
        connectionFactory.createConnection();







        // Close the database connection
        connectionFactory.closeConnection();
    }
}
