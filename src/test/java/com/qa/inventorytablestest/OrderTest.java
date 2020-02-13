package com.qa.inventorytablestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.inventorytables.Customer;
import com.qa.inventorytables.Item;
import com.qa.inventorytables.Order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


public class OrderTest {
	
	private Order orderItems;
	private Order orderNullId;
	private Order orderId;
	private List<Item> items = new ArrayList<>();
	@Before
	public void init() {
		items.add(new Item(1));
		items.add(new Item(2));
		items.add(new Item(3));
		this.orderItems = new Order(1,items,1.75);
		this.orderNullId = new Order();
		this.orderId = new Order(1);
	}
	
	@Test
	public void IdTest() {
		assertNotNull(orderItems.getId());
		assertNotNull(orderItems.getCustomerId());
		assertNotNull(orderItems.getTotalCost());
	}
	@Test
	public void IdsettersTest() {
		orderNullId.setId(5);
		orderNullId.setCustomerId(5);
		orderNullId.setItems(items);
		orderNullId.setTotalCost(0.50);
		assertEquals(5,orderNullId.getId());
		assertEquals(5,orderNullId.getCustomerId());
		assertEquals(items,orderNullId.getItems());
		assertEquals(0.50,orderNullId.getTotalCost(),0.2);
	}
	
	
	
	
	
	
	
	
	
	
	
}
