package main.service;

import main.item.impl.ElectronicsItem;
import main.item.impl.FragileItem;
import main.item.impl.GroceryItem;
import main.item.impl.InventoryItem;
import main.util.Detail;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static final String CSV_SEPARATOR = ",";
    private final String databaseName;
    public Database(String databaseName){
        this.databaseName = databaseName;
    }
    public static InventoryItem saveItem(String category, int id, int quantity, String name, double price, String description, double weight, List<Detail> details){
        switch(category){
            //using list to enter varargs is stackoverflow black magic, don't understand how it happens, go back to reading it
            //https://stackoverflow.com/questions/9863742/how-to-pass-an-arraylist-to-a-varargs-method-parameter
            case "Fragile" -> {
                return new FragileItem(id, quantity, name, price, description, weight, details.toArray(new Detail[0]));
            }
            case "Electronics" -> {
                return new ElectronicsItem(id, quantity, name, price, description, details.toArray(new Detail[0]));
            }
            case "Grocery" -> {
                return new GroceryItem(id, quantity, name, price, description, details.toArray(new Detail[0]));
            }
            default -> {
                return new InventoryItem();
            }
        }
    }

    public static void deleteItem(List<InventoryItem> items, int id){
        InventoryItem itemToBeRemoved = null;
        for(InventoryItem item: items){
            if(item.getId() == id){
                itemToBeRemoved = item;
            }
        }
        if(itemToBeRemoved != null) items.remove(itemToBeRemoved);
    }

    public void loadFromFile(List<InventoryItem> items){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(this.databaseName));
            String line = reader.readLine();
            while(line != null){
                InventoryItem item;
                int idx = 0;  //too lazy to keep track of whatever position in arr, so use incrementing counter to keep track
                String[] objectAsStringArr = line.split(",");
                int id = Integer.parseInt(objectAsStringArr[idx++]);
                int quantity = Integer.parseInt(objectAsStringArr[idx++]);
                String name = objectAsStringArr[idx++];
                String category = objectAsStringArr[idx++];
                double price = Double.parseDouble(objectAsStringArr[idx++]);
                double weight;
                if(category.equals("Fragile")){ //needed because parsedouble with argument null breaks program
                    weight = Double.parseDouble(objectAsStringArr[idx++]);
                }else{
                    weight = 0;
                    idx++;
                }
                idx += 2; //+2 on idx cuz we set perishable and breakable in counstructors themselves
                List<Detail> details = new ArrayList<>();
                String detail = objectAsStringArr[idx++];
                boolean breakLoop = false;
                while(!breakLoop){
                    if(detail.charAt(0) == '[') detail = detail.substring(1);
                    if(detail.charAt(detail.length() - 1) == ']') {
                        detail = detail.substring(0, detail.length() - 1);
                        idx--;
                        breakLoop = true;
                    }
                    if(detail.length() == 0) break;
                    details.add(Detail.valueOf(detail));
                    detail = objectAsStringArr[idx++].trim();
                }
                String description = objectAsStringArr[idx++];
                switch(category){
                    //using list to enter varargs is stackoverflow black magic, don't understand how it happens, go back to reading it
                    //https://stackoverflow.com/questions/9863742/how-to-pass-an-arraylist-to-a-varargs-method-parameter
                    case "Fragile" -> item = new FragileItem(id, quantity, name, price, description, weight, details.toArray(new Detail[0]));
                    case "Electronics" -> item = new ElectronicsItem(id, quantity, name, price, description, details.toArray(new Detail[0]));
                    case "Grocery" -> item = new GroceryItem(id, quantity, name, price, description, details.toArray(new Detail[0]));
                    default -> item = new InventoryItem();
                }
                items.add(item);
                line = reader.readLine();
            }
        }catch (IOException ignored){}
    }
    public void saveToFile(List<InventoryItem> items){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.databaseName), StandardCharsets.UTF_8));
            for(InventoryItem item: items){
                String line = item.getId() +
                        CSV_SEPARATOR +
                        item.getQuantity() +
                        CSV_SEPARATOR +
                        item.getName() +
                        CSV_SEPARATOR +
                        item.getCategory() +
                        CSV_SEPARATOR +
                        item.getPrice() +
                        CSV_SEPARATOR +
                        (item.getCategory().equals("Fragile") ? ((FragileItem) item).getWeightInKg() : "null") +
                        CSV_SEPARATOR +
                        item.isBreakable() +
                        CSV_SEPARATOR +
                        item.isPerishable() +
                        CSV_SEPARATOR +
                        item.getDetails().toString() +
                        CSV_SEPARATOR +
                        item.getDescription();
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ignored) {
        }
    }
}
