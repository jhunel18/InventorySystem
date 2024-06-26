/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.inventorysystem.Entities;

/**
 *
 * @author lenovo
 */
public class ProductsEntity {

    
    private String productName;
    private String description;
    private String quantity;

   
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean hasBlankFields() {
        return productName.isBlank() || description.isBlank() || quantity.isBlank();
    }
}
