package com.qa.inventoryTables;



public abstract class InventoryEntity {
	private int id;
	
	public InventoryEntity(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public abstract void userValues();
	
}
