package com.qa.inventoryTables;

import java.util.Scanner;

public class Item extends InventoryEntity {
	private String name = "";
	private double value;
	private int quanity;
	
	public Item(int id) {super(id);}

	public Item(String name, double value) {
		super(-1);
		this.setName(name);
		this.setValue(value);
	}
	public Item() {
		super(-1);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value2) {
		this.value = value2;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter item name");
		String name =input.nextLine();
		System.out.println("Enter item value");
		double value = input.nextDouble();
		this.name = name;
		this.value = value;
	}
}
