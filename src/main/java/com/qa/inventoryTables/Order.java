package com.qa.inventorytables;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Order extends InventoryEntity{
	public static final Logger LOGGER = Logger.getLogger(Order.class);
	
	private int customerId;
	private List<Item> items = new ArrayList<>();
	private double totalCost;
	
	
	public Order(int id) {
		super(id);
	}
	public Order(int customerId, List<Item> items, double totalCost) {
		super(-1);
		this.setCustomerId(customerId);
		this.setItems(items);
		this.setTotalCost(totalCost);
	}
	
	public Order() {
		super(-1);
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@SuppressWarnings("resource")
	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter order Id");
		int id =input.nextInt();
		this.customerId = id;

		

	}
	
	
	
	
	

}
