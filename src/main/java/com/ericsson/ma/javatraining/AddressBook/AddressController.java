package com.ericsson.ma.javatraining.AddressBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.ma.javatraining.XmlUtil.ReadXml;
import com.ericsson.ma.javatraining.XmlUtil.WriteXml;

public class AddressController {
	private final static String FILENAME = "src/main/resource/AddressBook.xml";
	private final static String ONENAME = "Person";
	private final static String CONFIGNAME ="AddressBook";
	private final static String[] XMLNAMES = {"Name", "Phone", "Address"};
	     
	private static Map<String, AddressEntity> map = new HashMap<String, AddressEntity>();
	
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
    protected AddressController(){}
	private static AddressController addressController = new AddressController();
    
    private static String filename = FILENAME;
    private static String onename = ONENAME;
    private static String configname = CONFIGNAME;
    private static String[] xmlnames = XMLNAMES;
    
    public static AddressController getInstance(){
    	map = read();
    	return addressController;
    }
    
    public static Map<String, AddressEntity> read(){
    	ReadXml readXml = new ReadXml();
    	List<List<String>> list = readXml.read(filename, onename, xmlnames);
		if(!list.isEmpty()){
			for(int i = 0; i < list.size(); i++){
				AddressEntity addressEntity = new AddressEntity(list.get(i));
				map.put(addressEntity.getPhone(), addressEntity);
			}	
		}
    	return map;
    }
    
    
    public Map<String, AddressEntity> getmap(){
    	return map;
    }
    
    public void write(){
    	WriteXml writeXml = new WriteXml(filename, configname, onename, xmlnames);
    	List<List<String>> list = new ArrayList<List<String>>();
    	Iterator<String> iterator = map.keySet().iterator();
    	while(iterator.hasNext()){
    		List<String> _list = new ArrayList<String>();
    		AddressEntity addressEntity = map.get(iterator.next());
    		_list.add(addressEntity.getName());
    		_list.add(addressEntity.getPhone());
    		_list.add(addressEntity.getAddress());
    		list.add(_list);
    	}
    	writeXml.write(list);
    }
    
    public boolean insert(AddressEntity addressEntity){
    	logger.info("Doing the insert function");
    	if(!map.containsKey(addressEntity.getPhone())){
    		map.put(addressEntity.getPhone(), addressEntity);
    		System.out.println("insert success");
    		this.write();
    	}
    	else{
    		System.out.println("the phone number is already existed");
    		return false;
    	}
    	return true;
    }
    
    public boolean delete(AddressEntity addressEntity){
    	logger.info("Doing the delete function");
    	if(map.containsKey(addressEntity.getPhone())){
    		map.remove(addressEntity.getPhone());
            System.out.println("remove success");
            this.write();
    	}
    	else{
    		System.out.println("the phone number is not existed");
    		return false;
    	}
    	return true;
    }
    
    public List<AddressEntity> search(String number){
    	logger.info("Doing the search function");
    	List<AddressEntity> list = new ArrayList<AddressEntity>();
    	AddressEntity addressEntity = null;
    	Iterator<String> iterator = map.keySet().iterator();
    	while(iterator.hasNext()){
    		addressEntity = map.get(iterator.next());
    		if(addressEntity.getPhone().contains(number)){
    			list.add(addressEntity);
    		}
    	}
    	return list;
    }
}
