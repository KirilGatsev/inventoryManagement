package main;

import main.testPackage.AbstractItem;
import main.testPackage.Detail;

public class InventoryItem extends AbstractItem {
    private int id;
    private int quantity;

    public void addDetail(Detail... details){
        for (Detail detail: details) {
            if(checkIfDetailCanBeAdded(detail)){
                this.details.add(detail);
            }
        }
    }

    //really bad way of implementing, will have to extend this every time we want to add support for a new detail
    private boolean checkIfDetailCanBeAdded(Detail detail){
        if(detail == Detail.BROKEN){
            return this.breakable;
        }else{
            return this.perishable;
        }
    }

    //removing broken goods == reducing quality and removing broken condition
    @Override
    public void handleBreakage(int percentage) {
        if(this.isBreakable() && this.details.contains(Detail.BROKEN)){
            this.quantity -= (this.quantity * percentage) / 100;
            this.details.remove(Detail.BROKEN);
        }else{
            System.out.println("No item in stock is broken.");
        }
    }

    //removing expired goods == reducing quality and removing expired condition
    @Override
    public void handlingExpiration(int percentage) {
        if(this.isPerishable() && this.details.contains(Detail.EXPIRED)){
            this.quantity -= (this.quantity * percentage) / 100;
            this.details.remove(Detail.EXPIRED);
        }else{
            System.out.println("No item in stock is expired.");
        }
    }

    public int getId(){
        return this.id;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public void sellItem(int quantity) {
        if(ensureQuantity(quantity)){
            System.out.println("You have purchased " + quantity + " of item " +
                    this.getName() + " for a total of " + calculateCost(quantity));
            this.quantity -= quantity;
        }else{
            System.out.println("We currently have only " + this.quantity + " of item " + this.getName());
        }
    }

     boolean ensureQuantity(int quantity){
        return this.getQuantity() >= quantity;
    }

    double calculateCost(int quantity){
        return this.getPrice() * quantity;
    }
}
