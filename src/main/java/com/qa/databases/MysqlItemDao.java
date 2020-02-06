package com.qa.databases;

import com.qa.inventoryTables.Item;

public class MysqlItemDao implements Dao<Item> {

	@Override
	public void create(Item t, Jdbc database) {
		database.Query("INSERT INTO items(name, value) VALUES('" + t.getName() + "','" + t.getValue() + "')");
	}

	@Override
	public void update(Item t, Jdbc database) {
		// TODO Auto-generated method stub
		if (t.getId() >= 0 && t.getName() != "" && t.getValue() != 0.0) {
			database.Query("Update items set name='" + t.getName() + "', value='" + t.getValue() + "' where id='"
					+ t.getId() + "'");
		}
	}

	@Override
	public void delete(Item t, Jdbc database) {
		if (t.getId() >= 0) {
			database.Query("DELETE FROM items where id='" + t.getId() + "'");
		}
	}

	@Override
	public String read(Item t, Jdbc database) {
		if (t.getId() >= 0) {
			return database.selectQuery("SELECT * FROM items WHERE id = '" + t.getId() + "'");
		} else if (t.getName() != "") {
			return database.selectQuery("SELECT * FROM items WHERE name = '" + t.getName() + "'");
		} else {
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
			System.out.println("you selected: " + item.getName() + "cost: " + item.getValue() );
			return item;
			}
			catch(NumberFormatException e) {
				System.out.println("invalid id");
				return new Item(-1);
			}
		}else {	return new Item(-1);}

	}

	@Override
	public String read(Jdbc database) {
		return database.selectQuery("SELECT * FROM items");
	}

}
