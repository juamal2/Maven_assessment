package Runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qa.databases.*;
import com.qa.inventoryTables.*;

import Interact.CreateQuery;

public class Runner {

	public static void main(String[] args) {
		// Create database connection and hold
		Jdbc database = new Jdbc();
		database.init("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
		
		MysqlItemDao item_sql = new MysqlItemDao();
		
		Item t = new Item("choco",1.50 );
		
		CreateQuery make = new CreateQuery();
		

		Order order = make.CreateOrderItems(1,database);
		
		System.out.println(order.getItems().get(0).getName());
		System.out.println(order.getItems().get(0).getValue());
		System.out.println(order.getItems().get(0).getQuanity());

		
		

		

		
		
		
		
		
		
	}

}
