package com.ericsson.ma.javatraining.AddressBook;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookTest {
    
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
	public void testMain(){
		File file = new File("src/test/resource/input.txt");
		try {
			FileOutputStream fos = new FileOutputStream("src/test/resource/real_output.txt");
			PrintStream out = new PrintStream(fos);
			InputStream in = new FileInputStream(file);
			AddressBook.userui(in, out);
			
			String res1 = "";
			String res2 = "";
			
			File file1 = new File("src/test/resource/real_output.txt");
			FileReader fd1 = new FileReader(file1);
			BufferedReader reader1 = new BufferedReader(fd1);
			String str1 = reader1.readLine();
			while(str1 != null){
				res1 += str1;
				str1 = reader1.readLine();
			}
			File file2 = new File("src/test/resource/output.txt");
			FileReader fd2 = new FileReader(file2);
			BufferedReader reader2 = new BufferedReader(fd2);
			String str2 = reader2.readLine();
			while(str2 != null){
				res2 += str2;
				str2 = reader2.readLine();
			}
			assertEquals(res1, res2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
