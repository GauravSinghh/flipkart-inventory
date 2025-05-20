package com.flipkart.inventory.model;

import java.util.List;

public class BrandFilter implements Filter{

    private List<String> brands;
    public BrandFilter(List<String> brands) {
        this.brands = brands;
    }
    public boolean apply(Item item) {
        return brands.contains(item.getBrand());
    }
}
