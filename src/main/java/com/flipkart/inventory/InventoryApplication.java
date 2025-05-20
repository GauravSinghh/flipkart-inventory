package com.flipkart.inventory;

import com.flipkart.inventory.config.ComparatorFactory;
import com.flipkart.inventory.model.BrandFilter;
import com.flipkart.inventory.model.CategoryFilter;
import com.flipkart.inventory.model.Filter;
import com.flipkart.inventory.model.PriceFilter;
import com.flipkart.inventory.service.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);

		InventoryService service = new InventoryService();

		service.addItem("Amul", "Milk", 100);
		service.addItem("Amul", "Curd", 50);
		service.addItem("Nestle", "Milk", 60);
		service.addItem("Nestle", "Curd", 90);

		service.addInventory("Amul", "Milk", 10);
		service.addInventory("Nestle", "Milk", 5);
		service.addInventory("Nestle", "Curd", 10);
		service.addInventory("Amul", "Milk", 10);
		service.addInventory("Amul", "Curd", 5);

		service.printInventory();

		System.out.println("*********************************************************");

		System.out.println("Search by brand: Nestle");
		List<Filter> filters = List.of(new BrandFilter(List.of("Nestle")));
		service.searchItems(filters, ComparatorFactory.getComparator("price", true))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by category: Milk, Order by price desc");
		filters = List.of(new CategoryFilter(List.of("Milk")));
		service.searchItems(filters, ComparatorFactory.getComparator("price", false))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by price range 70-100");
		filters = List.of(new PriceFilter(70, 100));
		service.searchItems(filters, ComparatorFactory.getComparator("price", true))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by category=Milk and price=70-100");
		filters = List.of(new CategoryFilter(List.of("Milk")), new PriceFilter(70, 100));
		service.searchItems(filters, ComparatorFactory.getComparator("price", false))
				.forEach(System.out::println);
	}

}
