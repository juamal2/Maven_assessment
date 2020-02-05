package com.qa.inventoryTables;

import java.util.ArrayList;

public abstract class InventoryEntitys {
	private int id;
	protected ArrayList<String> field_names = new ArrayList<String>();
	
	public InventoryEntitys(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
