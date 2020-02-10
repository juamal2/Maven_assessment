package com.qa.inventoryTables;

public class Customer extends InventoryEntity{
	
	private String name = "";
	public Customer(int id) {super(id);}

	public Customer(String name) {
		super(-1);
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
