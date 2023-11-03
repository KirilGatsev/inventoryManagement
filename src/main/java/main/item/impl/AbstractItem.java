package main.item.impl;

import main.item.functionality.*;
import main.util.Detail;

import java.util.Set;

public abstract class AbstractItem implements Item, Breakable, Categorizable, Perishable, Sellable {
    protected String name;
    private String category;
    protected Set<Detail> details; //protected as to not create setter
    private double price;
    protected boolean breakable;
    protected boolean perishable;
    protected String description; //protected as to not create setter

    public String getName(){
        return this.name;
    }

    @Override
    public boolean isBreakable() {
        return this.breakable;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public Set<Detail> getDetails() {
        return this.details;
    }

    @Override
    public double calculateValue() {
        return this.price;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isPerishable() {
        return this.perishable;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

}
