package com.ericsson.ma.javatraining.AddressBook;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressControllerTest {
	AddressController addressController;
    AddressEntity addressEntity;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		addressController = AddressController.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testInsert(){
		addressEntity = new AddressEntity(Arrays.asList("test", "111111", "test"));
		assertTrue(addressController.insert(addressEntity));
		assertFalse(addressController.insert(addressEntity));
	}
	
	@Test
	public void testDelete(){
		addressEntity = new AddressEntity(Arrays.asList("test", "111111", "test"));
		assertTrue(addressController.delete(addressEntity));
		assertFalse(addressController.delete(addressEntity));
	}
	
	@Test
	public void testSearch(){
		addressEntity = new AddressEntity(Arrays.asList("test", "111111", "test"));
		addressController.insert(addressEntity);
		List<AddressEntity> list = new ArrayList<AddressEntity>();
		list.add(addressEntity);
		assertEquals(list, addressController.search("111111"));
		addressEntity = new AddressEntity(Arrays.asList("test", "111111", "test"));
		addressController.delete(addressEntity);
	}
	
	
}
