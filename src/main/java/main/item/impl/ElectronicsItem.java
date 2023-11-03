package main.item.impl;

import main.util.Detail;

import java.util.HashSet;

public class ElectronicsItem extends InventoryItem {
    public ElectronicsItem(int id, int quantity, String name, double price,
                           String description, Detail... details){
        this.setId(id);
        this.setQuantity(quantity);
        this.name = name;
        this.setCategory("Electronics");
        this.details = new HashSet<>();
        this.breakable = false;
        this.perishable = false;
        this.addDetail(details);
        this.setPrice(price);
        this.description = description;
    }

    //less than 10 30% markup, more than or 10 20% markup
    @Override
    double calculateCost(int quantity){
        double priceBeforeTax = this.getPrice() * quantity;
        if(quantity < 10){
            return priceBeforeTax + (priceBeforeTax * 0.3);
        }else{
            return priceBeforeTax + (priceBeforeTax * 0.2);
        }
    }

    @Override
    public String toString() {
        return "ElectronicsItem{" +
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
