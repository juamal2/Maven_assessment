package com.qa.userinterface;

import java.util.Scanner;

import com.qa.Interact.CreateQuery;
import com.qa.databases.*;
import com.qa.inventoryTables.*;

public class Menu {

	Scanner input = new Scanner(System.in);
	Jdbc database;
	Dao dao;
	CreateQuery createquery = new CreateQuery();
	String menu;
	
	public Menu(Jdbc database) {
		this.database = database;
	}
	
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public boolean displayMain() {
		System.out.println("____________________________________________\n"
				+ "Customers\n"
				+ "Items\n"
				+ "Orders\n"
				+ "Exit \n");
		String choice = this.input.nextLine();
		choice = choice.toUpperCase();
		switch (choice){
			case ("CUSTOMERS"):
				this.dao = new MysqlCustomerDao();
				this.menu = "Customer";
				return true;
			case ("ITEMS"):
				this.dao = new MysqlItemDao();
				this.menu = "Items";
				return true;
			case ("ORDERS"):
				this.dao = new MysqlOrderDao();
				this.menu = "Order";
				return true;
			default:
				return false;
		}
		
	}
	public int displayCrud() {
		System.out.println("____________________________________________\n"
				+ "		"+this.menu+"\n"
				+ "Create\n"
				+ "Read\n"
				+ "Update\n"
				+ "Delete\n"
				+ "Exit \n");
		String choice = this.input.nextLine();
		choice = choice.toUpperCase();
		switch (choice){
		case ("Create"):
			return 1;
		case ("READ_ID"):
			int id = createquery.getId();
			System.out.println(dao.read(this.database, id));
			return 2;
		case ("READ ALL"):
			System.out.println(dao.read(this.database));
			return 3;
		case ("Update"):
			return 4;
		case ("Delete"):
			return 5;
		case ("Back"):
			return 6;
		default:
			return -1;
	}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
