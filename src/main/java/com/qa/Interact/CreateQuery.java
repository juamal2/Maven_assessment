package com.qa.Interact;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.qa.databases.*;
import com.qa.inventoryTables.Item;
import com.qa.inventoryTables.Order;

public class CreateQuery {
	
	public Order CreateOrderItems(int customer_id, Jdbc database){
		Scanner input = new Scanner(System.in);
		ArrayList<Item> items = new ArrayList<Item>();
		double totalCost = 0;
		MysqlItemDao itemsql = new MysqlItemDao();
		boolean appending = true;
		System.out.println("You are creating an order");
		while (appending) {
			System.out.println("Enter the id of the item you would like to add to your order or enter -1 to stop");
			int ans = input.nextInt();
			if (ans != -1) {
				Item sqli = new Item(ans);
				sqli = itemsql.getItemFromId(sqli, database);
				if (sqli.getId() != -1) {
					ans = 0;
					while (ans <= 0) {
						System.out.println("how many would you like to purchase? must be 1 or more");
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
