package com.flipkart.inventory;

import com.flipkart.inventory.config.ComparatorFactory;
import com.flipkart.inventory.model.*;
import com.flipkart.inventory.service.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
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

		System.out.println("Search by brand: Nestle, Order by price asc, qty desc");
		List<Filter> filters = List.of(new BrandFilter(List.of("Nestle")));
		List<SortCriteria> sortCriteriaList = Arrays.asList(
				new SortCriteria("price", true),
				new SortCriteria("itemqty", false)
		);
		service.searchItems(filters, ComparatorFactory.getComparator(sortCriteriaList))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by category: Milk, Order by price desc");
		filters = List.of(new CategoryFilter(List.of("Milk")));
		List<SortCriteria> sortCriteriaList1 = Arrays.asList(
				new SortCriteria("price", false));
		service.searchItems(filters, ComparatorFactory.getComparator(sortCriteriaList1))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by price range 70-100, order by price desc, order by qty asc");
		filters = List.of(new PriceFilter(70, 100));
		List<SortCriteria> sortCriteriaList2 = Arrays.asList(
				new SortCriteria("price", false),
				new SortCriteria("itemqty", true)
		);
		service.searchItems(filters, ComparatorFactory.getComparator(sortCriteriaList2))
				.forEach(System.out::println);

		System.out.println("*********************************************************");

		System.out.println("Search by category=Milk and price=70-100, order by brand desc, qty asc");
		List<SortCriteria> sortCriteriaList3 = Arrays.asList(
				new SortCriteria("brand", false),
				new SortCriteria("itemqty", true)
		);
		filters = List.of(new CategoryFilter(List.of("Milk")), new PriceFilter(70, 100));
		service.searchItems(filters, ComparatorFactory.getComparator(sortCriteriaList3))
				.forEach(System.out::println);
	}

}
