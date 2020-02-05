package com.qa.inventoryTables;

public class Item extends InventoryEntitys {
	private String name = "";
	private double value;
	private int quanity;
	
	public Item(int id) {super(id);}
	public Item(int id, String name, double value) {
		super(id);
		this.setName(name);
		this.setValue(value);
	}
	
	public Item(String name, double value) {
		super(-1);
		this.setName(name);
		this.setValue(value);
	}
	public Item(String name) {
		super(-1);
		this.setName(name);
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
}
