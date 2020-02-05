package com.qa.inventoryTables;

import java.util.ArrayList;

public class Order extends InventoryEntitys{
	private int customer_id;
	private ArrayList<Item> items = new ArrayList<Item>();
	private int total_cost;
	
	
	public Order(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Order(int customer_id, ArrayList<Item> items) {
		super(-1);
		this.setCustomer_id(customer_id);
		this.setItems(items);
	}
	
	
	
	
	
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	
	
	
	

}
