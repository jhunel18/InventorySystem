/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author lenovo
 */
public class DbConnector {
    
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "pwd";
    private static final String DEFAULT_DB = "postgres"; // Default database to connect to
    private static final String NEW_DATABASE_NAME = "pup_cims_db"; // New database name
    private static final String NEW_DATABASE_URL = JDBC_URL + NEW_DATABASE_NAME;
    
    
    public void createDatabase(){
        try {
            // Step 1: Create the new database if it does not exist
            createDatabaseIfNotExists();

            // Step 2: Use getConnection() to connect to the new database
            try (Connection connection = getConnection()) {
                System.out.println("Connected to the new database: " + NEW_DATABASE_NAME);
                // You can now execute further SQL statements on the new database
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    // Method to create the new database if it does not exist
    private static void createDatabaseIfNotExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + DEFAULT_DB, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {
            String createDatabaseSQL = "CREATE DATABASE " + NEW_DATABASE_NAME;
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Database " + NEW_DATABASE_NAME + " created successfully.");
        } catch (SQLException e) {
            // Check if the database already exists
            if (e.getSQLState().equals("42P04")) { // 42P04 is the SQLState code for 'database already exists'
                System.out.println("Database " + NEW_DATABASE_NAME + " already exists.");
            } else {
                throw e;
            }
        }
    }
    
    // Method to get a connection to the new database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(NEW_DATABASE_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
}
