package com.ericsson.ma.javatraining.XmlUtil;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadXmlTest {
    ReadXml readXml;
    String[] xmlNames = {"b1", "b2", "b3"};
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		readXml = new ReadXml();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead(){	
		List<List<String>> list = readXml.read("src/test/resource/test.xml", "b", xmlNames);
		List<String> _list1 = new ArrayList<String>();
		_list1.addAll(Arrays.asList("aaa", "bbb", "ccc"));
		List<String> _list2 = new ArrayList<String>();
		_list2.addAll(Arrays.asList("ddd", "eee", "fff"));
		List<List<String>> _list = new ArrayList<List<String>>();
		_list.add(_list1);
		_list.add(_list2);
		assertEquals(_list,list);
	}
	
	@Test
	public void testReadNotExisted(){
		List<List<String>> list = readXml.read("src/test/resource/testNotExisted.xml", "b", xmlNames);
		List<List<String>> _list = new ArrayList<List<String>>();
		assertEquals(_list,list);
	}
}
