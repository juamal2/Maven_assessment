package com.qa.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qa.Interact.CreateQuery;
import com.qa.databases.*;
import com.qa.inventoryTables.*;

public class Runner {

	public static void main(String[] args) {
		// Create database connection and hold
		Jdbc database = new Jdbc();
		database.init("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
		
//		Item item = new Item(1);
//		item.setQuanity(2);;
//		int key = 3;
//		System.out.println("INSERT INTO order_line(order_id, item_id, item_amount) values(" + key + "," + item.getId() + "," + item.getQuanity() + ")");
		
		
		
		CreateQuery make = new CreateQuery();
		
		
		Order order = make.CreateOrderItems(1,database);
		

		MysqlOrderDao orderSql = new MysqlOrderDao();
		
		orderSql.create(order, database);
		
		

		
		

		

		
		
		
		
		
		
	}

}
