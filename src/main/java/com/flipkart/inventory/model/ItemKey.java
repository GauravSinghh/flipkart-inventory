package com.flipkart.inventory.model;

import java.util.Objects;

public class ItemKey {
    private String brand;
    private String category;

    public ItemKey(String brand, String category) {
        this.brand = brand;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ItemKey key = (ItemKey) o;
        return this.brand.equals(key.brand) && this.category.equals(key.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, category);
    }
}
