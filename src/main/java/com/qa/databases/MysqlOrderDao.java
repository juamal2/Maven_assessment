package com.qa.databases;

import java.util.Scanner;

import com.qa.inventoryTables.Item;
import com.qa.inventoryTables.Order;

public class MysqlOrderDao implements Dao<Order> {

	@Override
	public void create(Order t, Jdbc database) {
		int key = database.Query("INSERT INTO orders(customer_id, cost) values(" + t.getCustomer_id() + "," + t.getTotal_cost() + ")");
		System.out.println(key);
		for (Item item : t.getItems()) {
			database.Query("INSERT INTO order_line(order_id, item_id, item_amount) values(" + key + "," + item.getId() + "," + item.getQuanity() + ")");
		}
	}

	@Override
	public void update(Order t, Jdbc database) {
		this.read(database, t.getId());
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the id of the item you want to update:");
		int itemId = input.nextInt();
		System.out.println("what is the new item amount");
		int amount = input.nextInt();
		database.Query("UPDATE order_line SET item_amount = " + amount + " WHERE order_id =" + t.getId() + " AND item_id = " + itemId);
	}

	@Override
	public void delete(Order t, Jdbc database) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			String order;
			order = database.selectQuery("SELECT * FROM orders WHERE id = '" + id + "'");
			return order += database.selectQuery("SELECT item_id, items.name,item_amount, items.value FROM order_line JOIN items ON order_line.id=items.id WHERE order_id ='"+ id + "'");
		}
		else {return "Failed";}
	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM orders");	
	}


}
