package com.qa.runner;



import org.apache.log4j.Logger;


import com.qa.databases.*;

import com.qa.userinterface.Menu;


public class Runner {
	public static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		// Create database connection and hold
		
		
		Jdbc database = new Jdbc();
		if (database.init("jdbc:mysql://35.187.121.181:3306/inventory", "root", "Superspider23")) {
			
			
			MysqlOrderDao.updateCosts(database);
			
			Menu menu = new Menu(database);
			while (menu.isRunning()) {
				if (!menu.displayMain()){
				}else {
					while(menu.displayCrud()) {
					}
				}
			}
			
			
			
		}
	}

}
