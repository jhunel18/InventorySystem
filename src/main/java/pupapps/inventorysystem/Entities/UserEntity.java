/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem.Entities;

/**
 *
 * @author lenovo
 */

public class UserEntity {
    private String fname;
    private String lname;
    private String username;
    private String email;
    private String password;
    
    // Constructor
    public UserEntity(String fname, String lname, String username, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
     public void setPassword(String password) { this.password = password; }
     
      public boolean hasBlankFields() {
        return fname.isBlank() || lname.isBlank() || username.isBlank() || email.isBlank() || password.isBlank();
    }
}


