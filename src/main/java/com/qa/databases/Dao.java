package com.qa.databases;

/**
 * Data access object, interface implement by classes that bridge between InventoryEntitys and Jdbc 
 * instances provides CRUD database functionality 
 * @author JuamalBlackman
 *	
 * @param <T> Child of InventoryEntity (Customer, Item, Order)
 */
public interface Dao<T> {
	
	/**
	 * Create record in table of T(InventoryEntity child instance) in Jdbc database
	 * @param t Instance Child of InventoryEntity (Customer, Item, Order)
	 * @param database Instance of Jdbc Class
	 */
	public void create(T t, Jdbc database);
	
	/**
	 * Update record in table of T(InventoryEntity child instance) in Jdbc database
	 * @param t t Instance Child of InventoryEntity (Customer, Item, Order)
	 * @param database Instance of Jdbc Class
	 */
	public void update(T t, Jdbc database);
	
	/**
	 * Delete record in table of T(InventoryEntity child instance) in Jdbc database
	 * @param t t Instance Child of InventoryEntity (Customer, Item, Order)
	 * @param database Instance of Jdbc Class
	 */
	public void delete(T t, Jdbc database);
	
	/**
	 * find and read record in table of T(InventoryEntity child instance) where id = parameter id
	 * to get all information use read without id parameter
	 * @param database Instance of Jdbc Class
	 * @param id Primary key being searched for in table
	 * @return returns formated string of record
	 */
	public String read(Jdbc database, int id);
	
	/**
	 * find and read all record from table T T(InventoryEntity child instance) to search by key pass
	 * primary key as parameter 
	 * @param database Instance of Jdbc Class
	 * @return returns formated string of all records in table
	 */
	public String read(Jdbc database);
	


}
