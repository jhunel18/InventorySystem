/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem;

import java.sql.SQLException;
import pupapps.inventorysystem.Forms.LoginForm;
import pupapps.inventorysystem.Forms.RegisterForm;
import pupapps.inventorysystem.Repository.Accounts;
import pupapps.inventorysystem.Repository.DbConnector;

/**
 *
 * @author lenovo
 */
public class UserManagement {
    private final Accounts accounts;
    RegisterForm registerForm;
    LoginForm loginForm;
    
    public UserManagement(DbConnector dbConnector) {
        this.loginForm = new LoginForm();
        this.registerForm = new RegisterForm();
        this.accounts = new Accounts(dbConnector);
    }
    public void showAppropriateForm() throws SQLException {
        accounts.createUsersTable(); // Ensure the table is created
        if (accounts.doesTableExist("users") && !accounts.isUserTableEmpty()) {
            loginForm.setVisible(true);
        } else {
            registerForm.setVisible(true);
        }
    }
}
