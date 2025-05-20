package com.flipkart.inventory.model;

public class PriceFilter implements Filter{

    private int from, to;
    public PriceFilter(int from, int to) {
        this.from = from; this.to = to;
    }
    public boolean apply(Item item) {
        return (from == -1 || item.getPrice() >= from) && (to == -1 || item.getPrice() <= to);
    }
}
