package main;

import main.testPackage.Detail;

import java.util.HashSet;

public class FragileItem extends InventoryItem {
    private double weightInKg;
    public FragileItem(int id, int quantity, String name, double price,
                       String description, double weightInKg, Detail... details){
        this.setId(id);
        this.setQuantity(quantity);
        this.name = name;
        this.setCategory("Electronics");
        this.details = new HashSet<>();
        this.addDetail(details);
        this.setPrice(price);
        this.description = description;
        this.breakable = true;
        this.perishable = false;
        this.weightInKg = weightInKg;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void sellItem(int quantity, int percentage) {
        if(super.ensureQuantity(quantity)){
            System.out.println("You have purchased " + quantity + " of item " +
                    this.getName() + " for a total of " + this.getPrice() * quantity);
            this.setQuantity(this.getQuantity() - quantity);
        }else{
            System.out.println("We currently have only " + this.getQuantity() + " of item " + this.getName());
        }
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
}
