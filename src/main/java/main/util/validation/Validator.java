package main.util.validation;

import main.util.Detail;
import main.item.impl.InventoryItem;
import main.util.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class  Validator {

    private Validator(){} //utility static class, don't want to be instantiable

    public static boolean isInteger(String input){
        char[] chars = input.toCharArray();
        for(char c: chars){
            if(!(c >= 48 && c <= 57)) return false;
        }
        return true;
    }

    public static boolean isDouble(String input){
        char[] chars = input.toCharArray();
        for(char c: chars){
            if(!((c >= 48 && c <= 57) || c == '.')) return false;
        }
        return true;
    }

    public static boolean isValidCategory(String input){
        return Constant.categories.contains(input);
    }

    public static InventoryItem itemExists(List<InventoryItem> items, int id){
        for(InventoryItem item : items){
            if(item.getId() == id) return item;
        }
        return null;
    }

}
