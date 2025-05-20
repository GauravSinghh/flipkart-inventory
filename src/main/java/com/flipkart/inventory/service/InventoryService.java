package com.flipkart.inventory.service;

import com.flipkart.inventory.exception.ItemNotFoundException;
import com.flipkart.inventory.model.Filter;
import com.flipkart.inventory.model.Item;
import com.flipkart.inventory.model.ItemKey;

import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {

    private Map<ItemKey, Item> inventory = new HashMap<>();

    public void addItem(String brand, String category, int price) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        if (price < 0){
            throw new IllegalArgumentException("Price can't be negative");
        }
        ItemKey key = new ItemKey(brand, category);
        inventory.putIfAbsent(key, new Item(brand, category, price));
//        if (!inventory.containsKey(key)) {
//            throw new ItemNotFoundException("Item not found: " + key);
//        }
    }

    public void addInventory(String brand, String category, int quantity) {
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity can't be negative");
        }
        ItemKey key = new ItemKey(brand, category);
        Item item = inventory.get(key);
        if (item == null) {
            throw new ItemNotFoundException("Item not found: " + key);
        }
//        item.(item.getQuantity()+quantity);
//        item.quantity += quantity;
        item.addQuantity(quantity);
    }

    public List<Item> searchItems(List<Filter> filters, Comparator<Item> comparator) {
        if (filters == null) {
            throw new IllegalArgumentException("No filters mentioned");
        }
        return inventory.values().stream()
                .filter(item -> filters.stream().allMatch(f -> f.apply(item)))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public void printInventory() {
        System.out.println("Current Inventory:");
        inventory.values().forEach(System.out::println);
    }
}
