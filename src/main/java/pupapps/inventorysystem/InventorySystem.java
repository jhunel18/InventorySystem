/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pupapps.inventorysystem;

import java.sql.SQLException;
import pupapps.inventorysystem.Repository.DbConnector;

/**
 *
 * @author lenovo
 */
public class InventorySystem {

    public static void main(String[] args) {
        DbConnector dbConnector = new DbConnector();
        dbConnector.createDatabase();
        
        UserManagement userManagement = new UserManagement(dbConnector);
       
        try {
            userManagement.showAppropriateForm();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
