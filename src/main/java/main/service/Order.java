package main.service;

import main.item.impl.InventoryItem;

import java.util.Scanner;

public final class Order {
    private Order(){}//utility static class, don't want to be instantiable
    public static void orderItem(InventoryItem item, int quantity) {
        if(ensureQuantity(item, quantity)){
            item.sellItem(quantity);
        }else{
            System.out.println("We currently have only " + item.getQuantity() + " of item " + item.getName());
        }
    }

    private static boolean ensureQuantity(InventoryItem item, int quantity){
        return item.getQuantity() >= quantity;
    }

}
