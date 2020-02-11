package com.qa.inventoryTables;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Order extends InventoryEntity{
	public static final Logger LOGGER = Logger.getLogger(Order.class);
	
	private int customer_id;
	private ArrayList<Item> items = new ArrayList<Item>();
	private double totalCost;
	
	
	public Order(int id) {
		super(id);
	}
	public Order(int customer_id, ArrayList<Item> items, double totalCost) {
		super(-1);
		this.setCustomer_id(customer_id);
		this.setItems(items);
		this.setTotalCost(totalCost);
	}
	
	public Order() {
		super(-1);
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public double getTotal_cost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost2) {
		this.totalCost = totalCost2;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter order Id");
		int id =input.nextInt();
		this.customer_id = id;

		

	}
	
	
	
	
	

}
