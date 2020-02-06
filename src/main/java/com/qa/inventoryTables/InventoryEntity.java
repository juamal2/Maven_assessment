package com.qa.inventoryTables;

import java.util.ArrayList;

public abstract class InventoryEntity {
	private int id;
	protected ArrayList<String> field_names = new ArrayList<String>();
	
	public InventoryEntity(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
