package com.qa.interact;

import java.util.ArrayList;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.qa.databases.*;
import com.qa.inventorytables.Item;
import com.qa.inventorytables.Order;


public class CreateQuery {
	public static final Logger LOGGER = Logger.getLogger(CreateQuery.class);
	
	Scanner input = new Scanner(System.in);
	
	/**
	 * Asks user for the primary key they are searching for
	 * @return user Integer input, should be validated after
	 */
	public int getId() {
		LOGGER.info("what id are your searching for:");
		return input.nextInt();
	}
	
	/**
	 * Create instance of Order class ready for OrderDao to execute with Jdbc query. 
	 * @param customer_id Id of customer who is making order EG: 1, all customers id's are stored in database
	 * @param database Instance of Jdbc database used to execute query Jdbc must be connected prior to function execution
	 * @return returns complete Instance of Order class for Jdbc query or OrderDao
	 */
	public Order createOrderItems(int customerId, Jdbc database){
		ArrayList<Item> items = new ArrayList<>();
		double totalCost = 0;
		MysqlItemDao itemsql = new MysqlItemDao();
		boolean appending = true;
		LOGGER.info("You are creating an order");
		while (appending) {
			LOGGER.info("Enter the id of the item you would like to add to your order or enter -1 to stop");
			int ans = input.nextInt();
			if (ans != -1) {
				Item sqli = new Item(ans);
				sqli = itemsql.getItemFromId(sqli, database);
				if (sqli.getId() != -1) {
					ans = 0;
					while (ans <= 0) {
						LOGGER.info("how many would you like to purchase? must be 1 or more");
						ans = input.nextInt();
					}
					sqli.setQuanity(ans);
					totalCost += sqli.getValue() * sqli.getQuanity();
					items.add(sqli);
				}
			}else if(ans == -1) {appending = false;}
		}
		return new Order(customerId, items, totalCost);
	}
}
