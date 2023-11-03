package main.cli;

import main.util.Detail;
import main.item.impl.InventoryItem;
import main.util.Constant;
import main.service.Database;
import main.service.Order;
import main.util.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    public static Scanner sc = new Scanner(System.in);
    public static void run(List<InventoryItem> items){
        while(true){
            System.out.println("To add item enter the following command: add");
            System.out.println("To remove item by id enter the following command: remove");
            System.out.println("To display all items enter the following command: display");
            System.out.println("To display items of a certain category enter the following command: display category");
            System.out.println("To place an order for an item enter the following command: place order");
            System.out.println("To stop the program enter the following command: exit");
            String command = sc.nextLine().toLowerCase().trim();
            if(command.equals("add")){
                addItemMenu(items);
            }else if(command.equals("remove")){
                deleteItemMenu(items);
            }else if(command.equals("display")){
                for(InventoryItem item: items){
                    System.out.println(item);
                }
                if(items.size() == 0) System.out.println("No items in database");
            }else if(command.equals("display category")){
                displayItemsFromCategoryMenu(items);
            }else if(command.equals("place order")){
                placeOrderMenu(items);
            }else if(command.equals("exit")){
                break;
            }
        }
    }

    private static void placeOrderMenu(List<InventoryItem> items){
        System.out.println("Enter how much you would like to order");
        String input = sc.nextLine();
        if(!Validator.isInteger(input)){
            System.out.println("Item quantity must be an integer");
            input = sc.nextLine();
        }
        int quantity = Integer.parseInt(input);
        InventoryItem item;
        while(true){
            System.out.println("Enter id of item you wish to order");
            input = sc.nextLine();
            while(!Validator.isInteger(input)){
                System.out.println("The item id should be an integer, please try again");
                input = sc.nextLine();
            }
            int id = Integer.parseInt(input);
            item = Validator.itemExists(items, id);
            if(item != null){
                Order.orderItem(item, quantity);
                break;
            }else{
                System.out.println("Item with that id does not exist");
            }
        }
    }

    private static void displayItemsFromCategoryMenu(List<InventoryItem> items){
        System.out.println("Enter category of items that you would wish to see");
        String input = sc.nextLine();
        String category = categoryInputMenu(input);
        boolean noItemsFromCategory = true;
        for(InventoryItem item : items){
            if(item.getCategory().equals(category)){
                System.out.println(item);
                noItemsFromCategory = false;
            }
        }
        if(noItemsFromCategory) System.out.println("No items from this category");
    }

    public static void deleteItemMenu(List<InventoryItem> items){
        System.out.println("Enter id of item to be deleted");
        String input = sc.nextLine();
        while(!Validator.isInteger(input)){
            System.out.println("ID must be an integer");
            input = sc.nextLine();
        }
        int id = Integer.parseInt(input);
        Database.deleteItem(items, id);
    }

    private static void addItemMenu(List<InventoryItem> items){
        String input;
        InventoryItem item;
        System.out.println("Please enter item id");
        int id = idInputMenu(items); //needed to get line inside function so we could validate unique ids
        System.out.println("Please enter item quantity");
        input = sc.nextLine();
        int quantity = quantityInputMenu(input);
        System.out.println("Please enter item name");
        String name = sc.nextLine();
        System.out.println("Please enter item category from list " + Constant.categories.toString());
        input = sc.nextLine();
        String category = categoryInputMenu(input);
        System.out.println("Please enter item price");
        input = sc.nextLine();
        double price = priceInputMenu(input);
        System.out.println("Please enter weight if item is fragile, else enter 0");
        input = sc.nextLine();
        double weight = weightInputMenu(input);
        System.out.println("Please enter details from the list separated by a comma" + Arrays.toString(Detail.values()));
        input = sc.nextLine();
        List<Detail> details = detailsInputMenu(input);
        System.out.println("Please enter item description");
        String description = sc.nextLine();
        item = Database.saveItem(category, id, quantity, name, price, description, weight, details);
        items.add(item);
    }

    private static int idInputMenu(List<InventoryItem> items){
        while(true){
            String input = sc.nextLine(); //need to get line here in order to validate unique ids
            while (!Validator.isInteger(input)) {
                System.out.println("The item id should be an integer, please try again");
                input = sc.nextLine();
            }
            int id = Integer.parseInt(input);
            if(items.stream().noneMatch(item -> item.getId() == id)){
                return id;
            }else{
                System.out.println("Item with id: " + id + " already exists");
            }
        }
    }

    private static int quantityInputMenu(String input){
        while(!Validator.isInteger(input)){
            System.out.println("The item quantity should be an integer, please try again");
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }

    private static String categoryInputMenu(String input){
        while(!Validator.isValidCategory(input)){
            System.out.println("The item category does not exist, please enter one of the following: " + Constant.categories);
            input = sc.nextLine();
        }
        return input;
    }

    private static double priceInputMenu(String input){
        while(!Validator.isDouble(input)){
            System.out.println("The item price should be numerical");
            input = sc.nextLine();
        }
        return Double.parseDouble(input);
    }

    private static double weightInputMenu(String input){
        while(!Validator.isDouble(input)){
            System.out.println("Weight should be numerical");
            input = sc.nextLine();
        }
        return Double.parseDouble(input);
    }

    private static List<Detail> detailsInputMenu(String input){
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .map(String::toUpperCase)
                .filter(detail -> Arrays.stream(Detail.values())
                        .map(Detail::toString).toList()
                        .contains(detail))
                .map(Detail::valueOf).toList();
    }
}
