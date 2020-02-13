package com.qa.databases;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.qa.inventorytables.Item;
import com.qa.inventorytables.Order;


public class MysqlOrderDao implements Dao<Order> {
	public static final Logger LOGGER = Logger.getLogger(MysqlOrderDao.class);
	
	@Override
	public void create(Order t, Jdbc database) {
		int key = database.query("INSERT INTO orders(customer_id, cost) values(" + t.getCustomerId() + "," + t.getTotalCost() + ")");
		LOGGER.info(key);
		for (Item item : t.getItems()) {
			database.query("INSERT INTO order_line(order_id, item_id, item_amount) values(" + key + "," + item.getId() + "," + item.getQuanity() + ")");
		}
	}
	@SuppressWarnings("resource")
	@Override
	public void update(Order t, Jdbc database) {
		this.read(database, t.getId());
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter the id of the item you want to update:");
		int itemId = input.nextInt();
		LOGGER.info("what is the new item amount");
		int amount = input.nextInt();
		database.query("UPDATE order_line SET item_amount = " + amount + " WHERE order_id =" + t.getId() + " AND item_id = " + itemId);
	}

	@SuppressWarnings("resource")
	@Override
	public void delete(Order t, Jdbc database) {
		this.read(database, t.getId());
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter the id of the item you want to delete");
		int itemId = input.nextInt();
		database.query("DELETE FROM order_line where order_id = "+ t.getId() + " and item_id =" + itemId);

	}

	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			String order;
			String items;
			order = database.selectQuery("SELECT * FROM orders WHERE id = '" + id + "'");
			items = database.selectQuery("SELECT item_id, items.name,item_amount, items.value FROM order_line JOIN items ON order_line.item_id=items.id WHERE order_id ='"+ id + "'");
			order = order += items;
			return order;
		}
		else {return "Failed";}
	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM orders");	
	}
	
	public static void  updateCosts(Jdbc database){
		String orders = database.selectQuery("SELECT id FROM orders");
		orders = orders.replace("id = ", "").trim().replace("\n","").replace("	 ","");
		char idCharArr[] = orders.toCharArray();
		for (char id : idCharArr) {
			int finalId = Character.getNumericValue(id);
			database.query("update orders set cost = (select sum(test.num1*test.num2) as totalcost from "+
					"(SELECT item_amount as num1, value as num2 FROM " +
							 "order_line JOIN items ON order_line.item_id=items.id WHERE " +
							 "order_line.order_id ="+ finalId + ") as test) where id =" + finalId);
		}
		
	}


}
