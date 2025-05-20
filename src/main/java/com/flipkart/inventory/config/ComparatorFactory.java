package com.flipkart.inventory.config;

import com.flipkart.inventory.model.Item;
import com.flipkart.inventory.model.SortCriteria;

import java.util.Comparator;
import java.util.List;

public class ComparatorFactory {
    public static Comparator<Item> getComparator(List<SortCriteria> criteriaList) {
        Comparator<Item> combinedComparator = null;

        for (SortCriteria criteria : criteriaList) {
            Comparator<Item> comparator;

            switch (criteria.getField()) {
                case "price":
                    comparator = Comparator.comparingInt(Item::getPrice);
                    break;
                case "itemqty":
                    comparator = Comparator.comparingInt(Item::getQuantity);
                    break;
                case "brand":
                    comparator = Comparator.comparing(Item::getBrand);
                    break;
                case "category":
                    comparator = Comparator.comparing(Item::getCategory);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported sort field: " + criteria.getField());
            }

            if (!criteria.isAscending()) {
                comparator = comparator.reversed();
            }

            combinedComparator = (combinedComparator == null) ? comparator : combinedComparator.thenComparing(comparator);
        }

        return combinedComparator != null ? combinedComparator : Comparator.comparing(Item::getBrand); // default fallback
    }
}

