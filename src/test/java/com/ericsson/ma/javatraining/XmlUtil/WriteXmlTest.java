package com.ericsson.ma.javatraining.XmlUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WriteXmlTest {
    WriteXml writeXml;
    String[] xmlnames = {"b1", "b2", "b3"};
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File("testbk.xml");
		if(!file.exists()){
			file.delete();
		}
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
	public void testWrite(){
		writeXml = new WriteXml("src/test/resource/testbk.xml", "a", "b", xmlnames);
		List<String> _list1 = new ArrayList<String>();
		_list1.addAll(Arrays.asList("aaa", "bbb", "ccc"));
		List<String> _list2 = new ArrayList<String>();
		_list2.addAll(Arrays.asList("ddd", "eee", "fff"));
		List<List<String>> _list = new ArrayList<List<String>>();
		_list.add(_list1);
		_list.add(_list2);
		writeXml.write(_list);
		
	}
	
	@Test
	public void testWriteExisted(){
		writeXml = new WriteXml("src/test/resource/testexisted.xml", "a", "b", xmlnames);
	}
}
