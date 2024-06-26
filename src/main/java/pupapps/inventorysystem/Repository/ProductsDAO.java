/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pupapps.inventorysystem.Entities.ProductsEntity;

/**
 *
 * @author lenovo
 */
public class ProductsDAO {
    private DbConnector dbConnector;  // Assume DBConnector is your class for database connections

    public ProductsDAO(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
    public void createProductsTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS products (
                         id SERIAL PRIMARY KEY,
                         product_name VARCHAR(50) NOT NULL,
                         description VARCHAR(50) NOT NULL,
                         quantity VARCHAR(50) NOT NULL
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    public void insertProduct(ProductsEntity product){
        String sql = "INSERT INTO products (product_name, description, quantity) VALUES (?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ProductsEntity> getAllProducts() {
        List<ProductsEntity> productsList = new ArrayList<>();
        String sql = "SELECT product_name, description, quantity FROM products";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductsEntity product = new ProductsEntity();
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getString("quantity"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }
}
