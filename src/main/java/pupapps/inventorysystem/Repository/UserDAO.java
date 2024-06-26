/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem.Repository;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pupapps.inventorysystem.Entities.UserEntity;
/**
 *
 * @author lenovo
 */
public class UserDAO {
    private DbConnector dbConnector;  // Assume DBConnector is your class for database connections

    public UserDAO(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
    
    public void insertUser(UserEntity user) throws SQLException {
        String insertQuery = "INSERT INTO users (fname, lname, username, email, password) VALUES (?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());  // Hash the password

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, user.getFname());
            pstmt.setString(2, user.getLname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    public boolean loginUser(UserEntity user) throws SQLException{
        String loginQuery = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(loginQuery)) {

            pstmt.setString(1, user.getUsername());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(user.getPassword(), storedHashedPassword)) {
                        return true;  // Password matches
                    }
                    else{
                        return false;
                    }
                }
                return false;  // Username not found or password does not match
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            throw e;
        }
    }
}
