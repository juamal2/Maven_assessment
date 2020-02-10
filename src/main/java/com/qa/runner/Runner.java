package com.qa.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qa.Interact.CreateQuery;
import com.qa.databases.*;
import com.qa.inventoryTables.*;
import com.qa.userinterface.Menu;


public class Runner {

	public static void main(String[] args) {
		// Create database connection and hold
		Jdbc database = new Jdbc();
		database.init("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
		
		
		Menu menu = new Menu(database);
		menu.displayMain();
		menu.displayCrud();

	}

}
