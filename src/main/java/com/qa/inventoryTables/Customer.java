package com.qa.inventoryTables;

import java.util.Scanner;


public class Customer extends InventoryEntity{
	
	private String name = "";
	public Customer(int id) {super(id);}

	public Customer(String name) {
		super(-1);
		this.setName(name);
	}
	public Customer() {
		super(-1);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter customer name");
		String name =input.nextLine();
		this.name = name;
	
	}
}
