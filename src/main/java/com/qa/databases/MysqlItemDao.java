package com.qa.databases;

import org.apache.log4j.Logger;

import com.qa.inventorytables.Item;


public class MysqlItemDao implements Dao<Item> {
	public static final Logger LOGGER = Logger.getLogger(MysqlItemDao.class);

	@Override
	public void create(Item t, Jdbc database) {
		database.query("INSERT INTO items(name, value) VALUES('" + t.getName() + "','" + t.getValue() + "')");
	}

	@Override
	public void update(Item t, Jdbc database) {
		if (t.getId() >= 0 && !t.getName().equals("") && t.getValue() != 0.0) {
			database.query("Update items set name='" + t.getName() + "', value='" + t.getValue() + "' where id='"
					+ t.getId() + "'");
		}
	}

	@Override
	public void delete(Item t, Jdbc database) {
		if (t.getId() >= 0) {
			database.query("DELETE FROM items where id = " + t.getId());
		}
	}

	@Override
	public String read(Jdbc database, int id) {
		if (id >= 0) {
			return database.selectQuery("SELECT * FROM items WHERE id = '" + id + "'");
		}
		else {
			return "Failed";
		}
	}
	public Item getItemFromId(Item t, Jdbc database) {
		if (t.getId() >= 0) {
			try {
			Item item = new Item(t.getId());
			String valueStr = database.selectQuery("SELECT value FROM items WHERE id = '" + t.getId() + "'");
			valueStr = valueStr.replace("value = ", "").trim();
			item.setValue(Double.parseDouble(valueStr));
			String nameStr = database.selectQuery("SELECT name FROM items WHERE id = '" + t.getId() + "'");
			item.setName(nameStr.replace("name = ", "").trim());
			LOGGER.info("you selected: " + item.getName() + "cost: " + item.getValue() );
			return item;
			}
			catch(NumberFormatException e) {
				LOGGER.info("invalid id");
				return new Item(-1);
			}
		}else {	return new Item(-1);}

	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM items");
	}

}
