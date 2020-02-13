package databasestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.databases.Jdbc;
import com.qa.databases.MysqlCustomerDao;
import com.qa.inventorytables.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MysqlCustomerDaoTest {
	@Mock
	private Jdbc database;
	


	MysqlCustomerDao customerDao = new MysqlCustomerDao();
	Customer customer = new Customer("awating delete");
	
	@Before
	@Test
	public void initConnectionTest() {
		this.database.init("jdbc:mysql://35.187.121.181:3306/inventory_test", "root_tester", "testy");
		
	}
	
	@Test
	public void createDeleteTest() {
		Customer customer = new Customer("john");
		customerDao.create(customer, database);
		Mockito.verify(database, Mockito.times(1)).query("INSERT INTO customers(name) VALUES('" + customer.getName() + "')");
	}
	
	
}



























