package com.qa.inventorytables;


/**
 * Parent of all inventory table classes
 * @author JuamalBlackman
 *
 */
public abstract class InventoryEntity {
	private int id;
	
	/**
	 * super constructor all children must have a valid id 
	 * use -1 for invalid id
	 * @param id
	 */
	public InventoryEntity(int id) {
		this.id = id;
	}
	
	/**
	 * id getter
	 * @return 
	 */
	public int getId() {
		return id;
	}
	/**
	 * id setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * abstract method used to set all unique attributes  
	 * @return 
	 */
	public abstract void userValues();
	
}
