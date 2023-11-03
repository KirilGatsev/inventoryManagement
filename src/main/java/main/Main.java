package main;

import main.item.impl.InventoryItem;
import main.service.Database;
import main.cli.CommandLineInterface;

import java.util.*;


public class Main {

    private static final String CSV_SEPARATOR = ",";

    public static void main(String[] args) {
        ArrayList<InventoryItem> items = new ArrayList<>();
        Database db = new Database("items.csv");
        db.loadFromFile(items);
        CommandLineInterface.run(items);
        db.saveToFile(items);
    }

}