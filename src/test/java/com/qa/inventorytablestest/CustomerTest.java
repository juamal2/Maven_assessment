package com.qa.inventorytablestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.inventorytables.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;




public class CustomerTest {
	
	@Spy
	@InjectMocks
	private Customer customer;

	
	private Customer customerNameTest;
	private Customer customerIdTest;
	private Customer customerNullId;
	@Before
	public void init(){
		this.customerNameTest = new Customer("Juamal");
		this.customerIdTest = new Customer(1);
		this.customerNullId = new Customer();
	}
	@Test
	public void setNameTest() {
		customerIdTest.setName("Juamal");
		assertEquals("Juamal", customerIdTest.getName());
	}
	@Test
	public void IdTest() {
		customerNameTest.setId(1);
		assertEquals(1, customerIdTest.getId());
		assertEquals(-1, customerNullId.getId());
	}

	
	
	
	

}
