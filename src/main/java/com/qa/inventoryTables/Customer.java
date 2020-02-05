package com.qa.inventoryTables;

public class Customer extends InventoryEntitys{
	
	private String name = "";
	public Customer(int id) {super(id);}
	public Customer(int id, String name){
		super(id);
		this.setName(name);
	}
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
