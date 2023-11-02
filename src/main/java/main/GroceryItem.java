package main;

import main.testPackage.Detail;

import java.util.HashSet;

public class GroceryItem extends InventoryItem{
    public GroceryItem(int id, int quantity, String name, double price,
                       String description, Detail... details){
        this.setId(id);
        this.setQuantity(quantity);
        this.name = name;
        this.setCategory("Electronics");
        this.details = new HashSet<>();
        this.addDetail(details);
        this.setPrice(price);
        this.description = description;
        this.breakable = false;
        this.perishable = true;
    }
    
}
