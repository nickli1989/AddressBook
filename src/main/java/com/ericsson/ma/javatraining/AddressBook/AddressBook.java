package com.ericsson.ma.javatraining.AddressBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AddressBook {

	private static Map<String, AddressEntity> map;
	private static AddressController controller;
	
	private static final Logger logger = LoggerFactory.getLogger(AddressBook.class);
	
	static {
		controller = AddressController.getInstance();
	}
	
	public static void main(String[] args) {
        userui(System.in, System.out);
	}

	public static void userui(InputStream in, PrintStream out){	
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		System.out.println("Type \"help\" to get help ...");
		logger.info("Starting to get command");
		while(true){
			try {				
				String cmd = reader.readLine();
				if(cmd.equals("help")){
					System.out.println("to insert:\n    insert [name] [phone] [address]\n" +
							"to delete:\n    delete [name] [phone] [address]\n" +
							"to search:\n    search [phone]\n" +
							"to print all:\n    printall\n" +
							"to quit:\n    quit\n");
				}
				else if (cmd.startsWith("insert")){
					String[] args = new String[]{};
					args = cmd.split(" ");
					List<String> arglist = new ArrayList<String>(Arrays.asList(args));
					arglist.remove(0);
					AddressEntity addressEntity = new AddressEntity(arglist);
					controller.insert(addressEntity);
				}
				else if (cmd.startsWith("delete")){
					String[] args = new String[]{};
					args = cmd.split(" ");
					List<String> arglist = new ArrayList<String>(Arrays.asList(args));
					arglist.remove(0);
					AddressEntity addressEntity = new AddressEntity(arglist);
					controller.delete(addressEntity);
				}
				else if (cmd.startsWith("search")){
					String[] args = new String[]{};
					args = cmd.split(" ");
					String phone = args[1];
					List<AddressEntity> list = controller.search(phone);
					if(!list.isEmpty()){
					     for( int i = 0; i < list.size(); i++){
						    out.println(list.get(i));
					    }
					}
					else{
						out.println("no such data");
					}
				}
				else if ("print all".equals(cmd)){
					map = controller.getmap();
					if(!map.isEmpty()){
						Iterator<String> iterator = map.keySet().iterator();
						while(iterator.hasNext()){
							AddressEntity addressEntity = map.get(iterator.next());
							out.println(addressEntity);
						}
					}
					else {
						out.println("no data");
					}
				}
				else if ("quit".equals(cmd)){
					logger.info("Break the loop");
					out.println("quit the address book");
					break;
				}
				else{
					out.println("please input the right command");
				}
			} catch (IOException e) {
				logger.error("Errors by failed or interrupted I/O operations");
				e.printStackTrace();
			}
			
		}
		
	}
}
