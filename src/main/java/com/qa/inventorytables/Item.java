package com.qa.inventorytables;

import java.util.Scanner;

import org.apache.log4j.Logger;



public class Item extends InventoryEntity {
	public static final Logger LOGGER = Logger.getLogger(Item.class);
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
	@SuppressWarnings("resource")
	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter item name");
		String nameInput =input.nextLine();
		LOGGER.info("Enter item value");
		double valueInput = input.nextDouble();
		this.name = nameInput;
		this.value = valueInput;

	}
}
