package com.qa.inventorytablestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.inventorytables.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

public class ItemTest {


	private Item ItemNameTest;
	private Item ItemIdTest;
	private Item ItemNullId;
	private Item item;
	
	@Before
	public void init(){
		this.ItemNameTest = new Item("Juamal", 0.50);
		this.ItemIdTest = new Item(1);
		this.ItemNullId = new Item();
		
	}
	
	@Test
	public void setNameTest() {
		ItemIdTest.setName("Juamal");
		assertEquals("Juamal", ItemIdTest.getName());
	}
	
	@Test
	public void IdTest() {
		ItemNameTest.setId(1);
		assertEquals(1, ItemIdTest.getId());
		assertEquals(-1, ItemNullId.getId());
	}
	
	@Test
	public void ValueTest() {
		ItemIdTest.setValue(0.50);
		ItemIdTest.setQuanity(5);
		assertEquals(0.50, ItemIdTest.getValue(), 0.02);
		assertEquals(5, ItemIdTest.getQuanity());
		assertNotNull(ItemIdTest.getQuanity());
		assertNotNull(ItemIdTest.getValue());
	}
	
	
	
	
	
	
	
	
	
	
}
