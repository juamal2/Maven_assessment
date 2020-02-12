package com.qa.userinterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.qa.databases.Dao;
import com.qa.databases.Jdbc;
import com.qa.databases.MysqlCustomerDao;
import com.qa.databases.MysqlItemDao;
import com.qa.databases.MysqlOrderDao;
import com.qa.interact.CreateQuery;
import com.qa.inventoryTables.Customer;
import com.qa.inventoryTables.InventoryEntity;
import com.qa.inventoryTables.Item;
import com.qa.inventoryTables.Order;

/**
 * main controller of console outputs start of the console application
 * @author JuamalBlackman
 *
 */
public class Menu {
	public static final Logger LOGGER = Logger.getLogger(Menu.class);
	Scanner input = new Scanner(System.in);
	Jdbc database;
	@SuppressWarnings("rawtypes")
	Dao dao;
	CreateQuery createquery = new CreateQuery();
	InventoryEntity tableItem;
	String menuLbl;
	
	public Menu(Jdbc database) {
		this.database = database;
	}
	
	@SuppressWarnings("rawtypes")
	public Dao getDao() {
		return dao;
	}

	public void setDao(@SuppressWarnings("rawtypes") Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * first part of Menu main loop for CMl inventory displays first main menu and takes user input
	 * assigns instance variables depends of choice to later be passed on to dao along with Jdbc database connection
	 * @return true while user input is not exit program
	 */
	public boolean displayMain() {
		LOGGER.info("____________________________________________\n"
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
				this.menuLbl = "Customer";
				return true;
			case ("ITEMS"):
				this.dao = new MysqlItemDao();
				this.tableItem = new Item();
				this.menuLbl = "Items";
				return true;
			case ("ORDERS"):
				this.dao = new MysqlOrderDao();
				this.tableItem = new Order();
				this.menuLbl = "Order";
				return true;
			default:
				return false;
		}
		
	}
	/**
	 * second part of Menu main loop for CMl inventory displays second main menu
	 * switch on user input and sends information out to relevant Dao
	 * @return returns Integer value depends on user input 
	 */
	@SuppressWarnings("unchecked")
	public int displayCrud() {
		int id;
		LOGGER.info("____________________________________________\n"
				+ "		"+this.menuLbl+"\n"
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
				orderCreate = this.createquery.createOrderItems(orderCreate.getCustomerId(), this.database);
				this.dao.create(orderCreate, this.database);
			}catch(ClassCastException failCast) {
				this.tableItem.userValues();
				this.dao.create(tableItem, this.database);
			}catch(InputMismatchException e) {
				LOGGER.info("stopping process invaild input");
			}
			return 1;
		case ("READ_ID"):
			id = this.createquery.getId();
			LOGGER.info(this.dao.read(this.database, id));
			return 2;
		case ("READ ALL"):
			LOGGER.info(this.dao.read(this.database));
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
