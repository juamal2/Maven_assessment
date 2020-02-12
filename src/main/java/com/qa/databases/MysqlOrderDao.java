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

	@Override
	public void update(Order t, Jdbc database) {
		this.read(database, t.getId());
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter the id of the item you want to update:");
		int itemId = input.nextInt();
		LOGGER.info("what is the new item amount");
		int amount = input.nextInt();
		database.query("UPDATE order_line SET item_amount = " + amount + " WHERE order_id =" + t.getId() + " AND item_id = " + itemId);
		input.close();
	}

	@Override
	public void delete(Order t, Jdbc database) {
		this.read(database, t.getId());
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter the id of the item you want to delete");
		int itemId = input.nextInt();
		database.query("DELETE FROM order_line where order_id = "+ t.getId() + " and item_id =" + itemId);
		input.close();
	}

	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			String order;
			String items;
			order = database.selectQuery("SELECT * FROM orders WHERE id = '" + id + "'");
			items = database.selectQuery("SELECT item_id, items.name,item_amount, items.value FROM order_line JOIN items ON order_line.id=items.id WHERE order_id ='"+ id + "'");
			items = order += items;
			return order;
		}
		else {return "Failed";}
	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM orders");	
	}


}
