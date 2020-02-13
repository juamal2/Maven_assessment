package com.qa.inventorytables;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;



/**
 * Class representing Customer table from database 
 * Instance of Customer = 1 record from Customer table
 * @author JuamalBlackman
 *
 */
public class Customer extends InventoryEntity{
	public static final Logger LOGGER = Logger.getLogger(Customer.class);
	
	private String name = "";
	public Customer(int id) {super(id);}

	public Customer(String name) {
		super(-1);
		this.setName(name);
	}
	public Customer() {
		super(-1);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("resource")
	@Override
	public void userValues() {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Enter customer name");
		this.name = input.nextLine();


	
	}
}
