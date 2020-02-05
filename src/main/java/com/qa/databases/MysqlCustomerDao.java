package com.qa.databases;
import com.qa.inventoryTables.Customer;

public class MysqlCustomerDao implements Dao<Customer> {
	// CREATE CUSTOMER
	@Override
	public void create(Customer t, Jdbc database) {
		database.Query("INSERT INTO customers(name) VALUES('" + t.getName() + "')");
	}
	// READ CUSTOMER
	@Override
	public String read(Customer t, Jdbc database) {
		if (t.getId() >= 0) {
			return database.selectQuery("SELECT * FROM customers WHERE id = '" + t.getId() + "'");
		}
		else if(t.getName() != "") {
			return database.selectQuery("SELECT * FROM customers WHERE name = '" + t.getName() + "'");
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
			database.Query("Update customers set name='" + t.getName() + "' where id='" + t.getId() + "'");
		}
	}
	// DELETE
	@Override
	public void delete(Customer t, Jdbc database) {
		if (t.getId() >= 0) {
			database.Query("DELETE FROM customers where id='" + t.getId() + "'");
		}
		else if(t.getName() != "") {
			database.Query("DELETE FROM customers where name='" + t.getName() + "'");
		}
	}
	

}
