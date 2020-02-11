package com.qa.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.qa.Interact.CreateQuery;
import com.qa.databases.*;
import com.qa.inventoryTables.*;

public class Menu {

	Scanner input = new Scanner(System.in);
	Jdbc database;
	Dao dao;
	CreateQuery createquery = new CreateQuery();
	InventoryEntity tableItem;
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
				this.tableItem = new Customer();
				this.menu = "Customer";
				return true;
			case ("ITEMS"):
				this.dao = new MysqlItemDao();
				this.tableItem = new Item();
				this.menu = "Items";
				return true;
			case ("ORDERS"):
				this.dao = new MysqlOrderDao();
				this.tableItem = new Order();
				this.menu = "Order";
				return true;
			default:
				return false;
		}
		
	}
	public int displayCrud() {
		int id;
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
		case ("CREATE"):
			try {
				Order orderCreate = (Order) tableItem;
				this.tableItem.userValues();
				orderCreate = this.createquery.createOrderItems(orderCreate.getCustomer_id(), this.database);
				this.dao.create(orderCreate, this.database);
			}catch(ClassCastException failCast) {
				this.tableItem.userValues();
				this.dao.create(tableItem, this.database);
			}catch(InputMismatchException e) {
				System.out.println("stopping process invaild input");
			}
			return 1;
		case ("READ_ID"):
			id = this.createquery.getId();
			System.out.println(this.dao.read(this.database, id));
			return 2;
		case ("READ ALL"):
			System.out.println(this.dao.read(this.database));
			return 3;
		case ("UPDATE"):
			this.tableItem.setId(this.createquery.getId());
			this.tableItem.userValues();
			this.dao.update(tableItem, this.database);
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
