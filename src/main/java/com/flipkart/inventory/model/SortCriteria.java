package com.flipkart.inventory.model;

public class SortCriteria {
    private String field;
    private boolean ascending;

    public SortCriteria(String field, boolean ascending) {
        this.field = field.toLowerCase();
        this.ascending = ascending;
    }

    public String getField() {
        return field;
    }

    public boolean isAscending() {
        return ascending;
    }
}

