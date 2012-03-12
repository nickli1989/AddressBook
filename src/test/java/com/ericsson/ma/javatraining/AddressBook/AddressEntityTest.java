package com.ericsson.ma.javatraining.AddressBook;


import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class AddressEntityTest {
    AddressEntity ae1;
    AddressEntity ae2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
    
	@Test
	public void testSetGet(){
		ae1 = new AddressEntity();
		ae1.setName("a");
		ae1.setPhone("1");
		ae1.setAddress("b");
		assertEquals("a", ae1.getName());
		assertEquals("1", ae1.getPhone());
		assertEquals("b", ae1.getAddress());
	}
	
	@Test
	public void testToString(){
		ae1 = new AddressEntity(Arrays.asList("a", "1", "b"));
		assertEquals("name=a, phone=1, address=b", ae1.toString());
	}
	
	@Test
	public void testEquals(){
		ae1 = new AddressEntity();
		ae1.setName("a");
		ae1.setPhone("1");
		ae1.setAddress("b");
		ae2 = new AddressEntity(Arrays.asList("a", "1", "b"));
		assertEquals(ae1, ae2);
	}
	
	@Test
	public void testNotEquals(){
		ae1 = new AddressEntity();
		ae1.setName("a");
		ae1.setPhone("1");
		ae1.setAddress("b");
		ae2 = new AddressEntity(Arrays.asList("a", "1", "a"));
		assertFalse(ae1.equals(ae2));
	}
}
