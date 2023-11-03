package main.item.functionality;

public interface Perishable {
    public boolean isPerishable();
    public void handlingExpiration(int percentage);

}
