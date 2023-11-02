package main;

public interface Sellable {
    public double getPrice();
    public void setPrice(double price);
    public void sellItem(int quantity);
}
