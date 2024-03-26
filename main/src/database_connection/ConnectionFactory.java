package database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * This class should use the Singleton pattern to ensure
 * only one instance of the connection is used throughout the application.
 * Singleton pattern restricts the instantiation of a class and
 * ensures that only one instance of the class exists in the Java Virtual Machine.
 * The singleton class must provide a global access point to get the instance of the class.
 */


public class ConnectionFactory {
    private static ConnectionFactory instance; // Singleton pattern
    private static final String URL = "jdbc:postgresql://localhost:5432/enrollment_system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private Connection connection; // Singleton pattern

    // Constructor
    private ConnectionFactory() {
        // Private constructor to prevent instantiation from outside
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            // Handle connection initialization failure
            System.err.println("Failed to establish database connection.");
            e.printStackTrace();
        }
    }

    // Singleton pattern
    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    // Method to create and return the database connection
    public Connection createConnection() {
        return connection;
    }

    public void closeConnection(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (SQLException e){
            // Handle connection closing failure
            System.err.println("Failed to close database connection.");
            e.printStackTrace();
        }
    }

}