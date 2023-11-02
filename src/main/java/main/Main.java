package main;

import main.testPackage.Detail;


public class Main {
    public static void main(String[] args) {
        InventoryItem item = new ElectronicsItem(1, 30, "iPhone", 10, "Test desc");
//        item.handleBreakage(35);
//        System.out.println(item.getQuantity());
//        item.sellItem(20);
//        System.out.println(item.getQuantity());
//        item.sellItem(20);
//        System.out.println(item.getQuantity());
        InventoryItem item2 = new GroceryItem(2, 30, "Carrot",
                2, "Carrot ^_^", Detail.EXPIRED);
        System.out.println(item2.getQuantity());
        item2.handlingExpiration(30);
        System.out.println(item2.getQuantity());
        item2.handlingExpiration(30);
        item2.addDetail(Detail.BROKEN);
        item2.handlingExpiration(30);
        System.out.println(item2.getQuantity());
        item2.handlingExpiration(30);
        item.sellItem(11);
        InventoryItem item3 = new FragileItem(3, 4, "My heart", 3.33,
                "Organ", 0.3);
    }
}