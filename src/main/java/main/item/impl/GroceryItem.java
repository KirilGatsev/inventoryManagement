package main.item.impl;

import main.util.Detail;

import java.util.HashSet;

public class GroceryItem extends InventoryItem {
    public GroceryItem(int id, int quantity, String name, double price,
                       String description, Detail... details){
        this.setId(id);
        this.setQuantity(quantity);
        this.name = name;
        this.setCategory("Grocery");
        this.details = new HashSet<>();
        this.breakable = false;
        this.perishable = true;
        this.addDetail(details);
        this.setPrice(price);
        this.description = description;
    }
    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + getId() +
                ", quantity=" + getQuantity() +
                ", name='" + name + '\'' +
                ", price=" + this.getPrice() +
                ", details=" + details +
                ", breakable=" + breakable +
                ", perishable=" + perishable +
                ", description='" + description + '\'' +
                '}';
    }

}
