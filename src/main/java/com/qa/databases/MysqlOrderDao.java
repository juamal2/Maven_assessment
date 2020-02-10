package com.qa.databases;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Order t, Jdbc database) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			return database.selectQuery("SELECT * FROM orders WHERE id = '" + id + "'");
		}
		else {return "Failed";}
	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM orders");	
	}


}
