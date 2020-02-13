package databasestest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.databases.Jdbc;
import com.qa.inventorytables.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcTest {
	
	private Jdbc database = new Jdbc();
	private Jdbc databaseFail = new Jdbc();
	private ResultSet resultset;
	private Statement statement; 
	
	@Before
	@Test
	public void initConnectionTest() {
		assertTrue(this.database.init("jdbc:mysql://35.187.121.181:3306/inventory_test", "root_tester", "testy"));
		
	}
	@Test
	public void initTest() {
		assertFalse(this.databaseFail.init("jdbc:mysql://35.187.121.181:3306/inventory_test", "root_test", "testy"));
	}
	
	@Test
	public void gettersTest() {
		assertEquals(statement,database.getStmt());
		assertEquals(resultset,database.getResultSet());
		assertNotNull(database.getConnection());
	}
	
	@Test
	public void selectTest() {
		assertEquals("name = name1",database.selectQuery("select name from customers where id = 1").trim());
		assertEquals("failed",database.selectQuery("select xxx from customers where id = 1").trim());
	}
	@Test
	public void queryTest() {
		
		assertEquals(4, database.query("INSERT INTO customers(id, name) values(4 ,'jackie')"));
		assertEquals(-1, database.query("upde customers set name = 'jack' where id = 4"));
		assertEquals(-1, database.query("update customers set name = 'jack' where id = 4"));
		assertEquals(-1, database.query("DELETE FROM customers WHERE id = 4"));
	}
	
}
