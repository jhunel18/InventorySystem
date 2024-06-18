/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pupapps.inventorysystem.Repository;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author lenovo
 */
public class Accounts {
     private static DbConnector dbConnector;

    public Accounts(DbConnector dbConnector) {
        Accounts.dbConnector = dbConnector;
    }
    //Method for Creating User's Table
    public void createUsersTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS users (
                         id SERIAL PRIMARY KEY,
                         fname VARCHAR(50) NOT NULL,
                         lname VARCHAR(50) NOT NULL,
                         username VARCHAR(50) UNIQUE NOT NULL,
                         email VARCHAR(50) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    
    public boolean doesTableExist(String tableName) throws SQLException {
        try (Connection conn = dbConnector.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
                return rs.next();
            }
        }
    }
    
    public boolean isUserTableEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) AS rowcount FROM users";
        try (Connection conn = dbConnector.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("rowcount") == 0;
            }
        }
        return true; // If there is an error, assume the table is empty
    }
      
}
