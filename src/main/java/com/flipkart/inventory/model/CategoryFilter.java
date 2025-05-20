package com.flipkart.inventory.model;

import java.util.List;

public class CategoryFilter implements Filter{

    private List<String> categories;
    public CategoryFilter(List<String> categories) {
        this.categories = categories;
    }
    public boolean apply(Item item) {
        return categories.contains(item.getCategory());
    }
}
