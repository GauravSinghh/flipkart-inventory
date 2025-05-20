package com.flipkart.inventory.config;

import com.flipkart.inventory.model.Item;

import java.util.Comparator;

public class ComparatorFactory {
    public static Comparator<Item> getComparator(String field, boolean asc) {
        Comparator<Item> comparator;
        switch (field.toLowerCase()) {
            case "price":
                comparator = Comparator.comparingInt(item -> item.getPrice());
                break;
            case "itemqty":
                comparator = Comparator.comparingInt(item -> item.getQuantity());
                break;
            default:
                comparator = Comparator.comparing(item -> item.getBrand());
        }
        return asc ? comparator : comparator.reversed();
    }
}
