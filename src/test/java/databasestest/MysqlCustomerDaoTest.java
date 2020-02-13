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
	Customer customerName = new Customer("john");
	Customer customer = new Customer(1);
	Customer customerNull = new Customer();
	@Before
	@Test
	public void initCustomersConnectionTest() {
		this.database.init("jdbc:mysql://35.187.121.181:3306/inventory_test", "root_tester", "testy");
		
	}
	@Test
	public void createTest() {
		customerDao.create(customerName, database);
		Mockito.verify(database, Mockito.times(1)).query("INSERT INTO customers(name) VALUES('" + customerName.getName() + "')");
	}
	@Test
	public void deleteTest() {
		customerDao.delete(customer, database);
		Mockito.verify(database, Mockito.times(1)).query("DELETE FROM customers where id='" + customer.getId() + "'");
	}
	@Test
	public void deleteFailTest() {
		customerDao.delete(customerNull, database);
		Mockito.verify(database, Mockito.times(0)).query("DELETE FROM customers where id='" + customerNull.getId() + "'");
	}
	
	
	@Test
	public void updateTest() {
		customer.setName("name");
		customerDao.update(customer, database);
		Mockito.verify(database, Mockito.times(1)).query("Update customers set name='" + customer.getName() + "' where id='" + customer.getId() + "'");
	}
	
	@Test
	public void updateFailTest() {
		customerDao.update(customerNull, database);
		Mockito.verify(database, Mockito.times(0)).query("Update customers set name='" + customerNull.getName() + "' where id='" + customerNull.getId() + "'");
	}
	
	@Test
	public void readAllTest(){
		customerDao.read(database);
		Mockito.verify(database, Mockito.times(1)).selectQuery("SELECT * FROM customers");
	}
	
	@Test
	public void readIdTest() {
		customerDao.read(database, customer.getId());
		Mockito.verify(database, Mockito.times(1)).selectQuery("SELECT * FROM customers WHERE id = '" + customer.getId() + "'");
	}
	
	@Test
	public void readIdFailTest() {
		customerDao.read(database, customerNull.getId());
		Mockito.verify(database, Mockito.times(0)).selectQuery("SELECT * FROM customers WHERE id = '" + customerNull.getId() + "'");
	}
	
	
}



























