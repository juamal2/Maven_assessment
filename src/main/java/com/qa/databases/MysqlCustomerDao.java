package com.qa.databases;


import com.qa.interact.CreateQuery;
import com.qa.inventorytables.Customer;



public class MysqlCustomerDao implements Dao<Customer> {
	
	CreateQuery createquery = new CreateQuery();
	// CREATE CUSTOMER
	@Override
	public void create(Customer t, Jdbc database) {
		database.query("INSERT INTO customers(name) VALUES('" + t.getName() + "')");
	}
	// READ CUSTOMER
	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			return database.selectQuery("SELECT * FROM customers WHERE id = '" + id + "'");
		}
		else {return "Failed";}
	}
	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM customers");	
	}
	// Update
	@Override
	public void update(Customer t, Jdbc database) {
		if (t.getId() >= 0) {
			database.query("Update customers set name='" + t.getName() + "' where id='" + t.getId() + "'");
		}
	}
	// DELETE
	@Override
	public void delete(Customer t, Jdbc database) {
		if (t.getId() >= 0) {
			database.query("DELETE FROM customers where id='" + t.getId() + "'");
		}
		else if(!t.getName().equals("")) {
			database.query("DELETE FROM customers where name='" + t.getName() + "'");
		}
	}
	

}
