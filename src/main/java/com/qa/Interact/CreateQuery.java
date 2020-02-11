package com.qa.Interact;

import java.util.ArrayList;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.qa.databases.*;

import com.qa.inventoryTables.Item;
import com.qa.inventoryTables.Order;


public class CreateQuery {
	public static final Logger LOGGER = Logger.getLogger(CreateQuery.class);
	
	Scanner input = new Scanner(System.in);
	public int getId() {
		LOGGER.info("what id are your searching for:");
		int id = input.nextInt();
		return id;
	}
	
	public Order createOrderItems(int customer_id, Jdbc database){
		ArrayList<Item> items = new ArrayList<Item>();
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
		Order final_order = new Order(customer_id, items, totalCost);
		return final_order;
	}
}
