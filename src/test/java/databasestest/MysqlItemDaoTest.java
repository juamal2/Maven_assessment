package databasestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.databases.Jdbc;
import com.qa.databases.MysqlItemDao;
import com.qa.inventorytables.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MysqlItemDaoTest {
	@Mock
	private Jdbc database;
	


	MysqlItemDao itemDao = new MysqlItemDao();
	
	Item itemValue = new Item("john", 0.05);
	Item item = new Item(1);
	Item itemNull = new Item();
	
	@Before
	@Test
	public void initItemsConnectionTest() {
		this.database.init("jdbc:mysql://35.187.121.181:3306/inventory_test", "root_tester", "testy");
	}
	
	@Test
	public void createTest() {
		itemDao.create(itemValue, database);
		Mockito.verify(database, Mockito.times(1)).query("INSERT INTO items(name, value) VALUES('" + itemValue.getName() + "','" + itemValue.getValue() + "')");
	}
	
	@Test
	public void updateTest() {
		itemValue.setId(1);
		itemDao.update(itemValue, database);
		Mockito.verify(database, Mockito.times(1)).query("Update items set name='" + itemValue.getName() + "', value='" + itemValue.getValue() + "' where id='"
				+ itemValue.getId() + "'");
	}
	
	@Test
	public void updateFailIdTest() {
		itemDao.update(itemValue, database);
		Mockito.verify(database, Mockito.times(0)).query("Update items set name='" + itemValue.getName() + "', value='" + itemValue.getValue() + "' where id='"
				+ itemValue.getId() + "'");
	}
	
	@Test
	public void updateFailNameTest() {
		itemValue.setName("");
		Mockito.verify(database, Mockito.times(0)).query("Update items set name='" + itemValue.getName() + "', value='" + itemValue.getValue() + "' where id='"
				+ itemValue.getId() + "'");
	}
	
	@Test
	public void updateFailValueTest() {
		itemValue.setValue(-1);
		itemDao.update(itemValue, database);
		Mockito.verify(database, Mockito.times(0)).query("Update items set name='" + itemValue.getName() + "', value='" + itemValue.getValue() + "' where id='"
				+ itemValue.getId() + "'");
	}
	
	@Test
	public void deleteTest() {
		itemDao.delete(item, database);
		Mockito.verify(database, Mockito.times(1)).query("DELETE FROM items where id = " + item.getId());
	}
	
	@Test
	public void deleteFailTest() {
		itemDao.delete(itemNull, database);
		Mockito.verify(database, Mockito.times(0)).query("DELETE FROM items where id = " + itemNull.getId());
	}
	
	@Test
	public void readTest() {
		itemDao.read(database);
		Mockito.verify(database, Mockito.times(1)).selectQuery("SELECT * FROM items");
	}
	
	@Test
	public void readIdTest(){
		itemDao.read(database, item.getId());
		Mockito.verify(database, Mockito.times(1)).selectQuery("SELECT * FROM items WHERE id = '" + item.getId() + "'");
	}
	
	@Test
	public void readIdFailTest() {
		itemDao.read(database, itemNull.getId());
		Mockito.verify(database, Mockito.times(0)).selectQuery("SELECT * FROM items WHERE id = '" + itemNull.getId() + "'");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
