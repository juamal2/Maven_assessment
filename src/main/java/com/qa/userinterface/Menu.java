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
import com.qa.inventorytables.Customer;
import com.qa.inventorytables.InventoryEntity;
import com.qa.inventorytables.Item;
import com.qa.inventorytables.Order;

/**
 * main controller of console outputs start of the console application
 * 
 * @author JuamalBlackman
 *
 */
public class Menu {
	public static final Logger LOGGER = Logger.getLogger(Menu.class);
	private Scanner input = new Scanner(System.in);
	private boolean running = true;
	private Jdbc database;
	@SuppressWarnings("rawtypes")
	private Dao dao;
	private CreateQuery createquery = new CreateQuery();
	private InventoryEntity tableItem;
	private String menuLbl;

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
	 * first part of Menu main loop for CMl inventory displays first main menu and
	 * takes user input assigns instance variables depends of choice to later be
	 * passed on to dao along with Jdbc database connection
	 * 
	 * @return true while user input is not exit program
	 */
	public boolean displayMain() {
		
		LOGGER.info(
				"____________________________________________\n" + "Customers\n" + "Items\n" + "Orders\n" + "Exit \n");
		String choice = this.input.nextLine();
		choice = choice.toUpperCase();
		switch (choice) {
		case ("CUSTOMERS"):
			this.dao = new MysqlCustomerDao();
			this.tableItem = new Customer();
			this.menuLbl = "Customer";
			break;
		case ("ITEMS"):
			this.dao = new MysqlItemDao();
			this.tableItem = new Item();
			this.menuLbl = "Items";
			break;
		case ("ORDERS"):
			this.dao = new MysqlOrderDao();
			this.tableItem = new Order();
			this.menuLbl = "Orders";
			break;
		case ("EXIT"):
			this.running = false;
			return false;
		default:
			LOGGER.info("invalid input please enter one of the above");
		}
		return true;
	}

	/**
	 * second part of Menu main loop for CMl inventory displays second main menu
	 * switch on user input and sends information out to relevant Dao
	 * 
	 * @return returns Integer value depends on user input
	 */
	@SuppressWarnings("unchecked")
	public boolean displayCrud() {
		int id;
		MysqlOrderDao.updateCosts(database);
		InventoryEntity methTableItem = this.tableItem;
		LOGGER.info("____________________________________________\n" + "		" + this.menuLbl + "\n" + "Create\n"
				+ "Read\n" + "Update\n" + "Delete\n" + "Exit \n");
		String choice = this.input.nextLine();
		choice = choice.toUpperCase();
		switch (choice) {
		case ("CREATE"):
			try {
				Order orderCreate = (Order) methTableItem;
				methTableItem.userValues();
				orderCreate = this.createquery.createOrderItems(orderCreate.getCustomerId(), this.database);
				this.dao.create(orderCreate, this.database);
			} catch (ClassCastException failCast) {
				methTableItem.userValues();
				this.dao.create(methTableItem, this.database);
			} catch (InputMismatchException e) {
				LOGGER.info("stopping process invaild input");
			}
			break;
		case ("READ_ID"):
			id = this.createquery.getId();
			LOGGER.info(this.dao.read(this.database, id));
			break;
		case ("READ ALL"):
			LOGGER.info(this.dao.read(this.database));
			break;
		case ("UPDATE"):
			methTableItem.setId(this.createquery.getId());
			methTableItem.userValues();
			this.dao.update(methTableItem, this.database);
			break;
		case ("DELETE"):
			methTableItem.setId(createquery.getId());
			this.dao.delete(methTableItem, this.database);
			break;
		case ("EXIT"):
			methTableItem = null;
			this.dao = null;
			return false;
		default:
			LOGGER.info("invalid input please enter one of the above");
			break;
		}
	return true;
	}
	
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
