package main.item.impl;

import main.util.Detail;

import java.util.HashSet;

public class FragileItem extends InventoryItem {
    private double weightInKg;
    public FragileItem(int id, int quantity, String name, double price,
                       String description, double weightInKg, Detail... details){
        this.setId(id);
        this.setQuantity(quantity);
        this.name = name;
        this.setCategory("Fragile");
        this.details = new HashSet<>();
        this.breakable = true;
        this.perishable = false;
        this.addDetail(details);
        this.setPrice(price);
        this.description = description;
        this.weightInKg = weightInKg;
    }

    public double getWeightInKg() {
        return weightInKg;
    }


    private boolean aboveWeightThreshold(){
        return this.weightInKg >= 1.5;
    }

    //30% if above, 20 if below
    double calculateCost(int quantity){
        double priceBeforeTax = this.getPrice() * quantity;
        if(this.aboveWeightThreshold()){
            return priceBeforeTax + (priceBeforeTax * 0.3);
        }else{
            return priceBeforeTax + (priceBeforeTax * 0.2);
        }
    }

    @Override
    public String toString() {
        return "FragileItem{" +
                "id=" + getId() +
                ", quantity=" + getQuantity() +
                ", weightInKg=" + weightInKg +
                ", name='" + name + '\'' +
                ", price=" + this.getPrice() +
                ", details=" + details +
                ", breakable=" + breakable +
                ", perishable=" + perishable +
                ", description='" + description + '\'' +
                '}';
    }
}
