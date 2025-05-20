package com.flipkart.inventory.model;

public class Item {
    private String brand;
    private String category;
    private int price;
    private int quantity;

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }


    public Item(String brand, String category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = 0;
    }

    public String toString() {
        return "Brand: " + this.brand + ", Category: " +  this.category + ", Price: " + this.price + ", Quantity: " + this.quantity;
    }
}