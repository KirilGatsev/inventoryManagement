package main;

import main.testPackage.Detail;

import java.util.HashSet;

public class ElectronicsItem extends InventoryItem{
    public ElectronicsItem(int id, int quantity, String name, double price,
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
        this.perishable = false;
    }

    @Override
    public void sellItem(int quantity) {
        if(this.getQuantity() >= quantity){
            System.out.println("You have purchased " + quantity + " of item " +
                    this.getName() + " for a total of " + calculateCost(quantity));
            this.setQuantity(this.getQuantity() - quantity);
        }else{
            System.out.println("We currently have only " + this.getQuantity() + " of item " + this.getName());
        }
    }

    //less than 10 30% markup, more than or 10 20% markup
    double calculateCost(int quantity){
        double priceBeforeTax = this.getPrice() * quantity;
        if(quantity < 10){
            return priceBeforeTax + (priceBeforeTax * 0.3);
        }else{
            return priceBeforeTax + (priceBeforeTax * 0.2);
        }
    }

}
