package com.ericsson.ma.javatraining.AddressBook;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class AddressEntity {
    private String name;
    private String phone;
    private String address;
    
    private static final Logger logger = LoggerFactory.getLogger(AddressEntity.class);
    
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public AddressEntity(){
		this.name = "";
		this.phone = "";
		this.address = "";
	}
	public AddressEntity(List<String> list){
		if(list.size() == 3){
			this.name = list.get(0);
			this.phone = list.get(1);
			this.address = list.get(2);
		}
		logger.info("One AddressEntity (" + this.name +"," + this.phone + "," + this.address + ") constructed!");
	}
	@Override
	public String toString(){
		return "name=" + name + ", phone=" + phone +", address=" + address;
	}
	@Override
	public boolean equals(Object object){
		if(object instanceof AddressEntity){
			if(name.equals(((AddressEntity)object).getName())
					&&phone.equals(((AddressEntity)object).getPhone())
					&&address.equals(((AddressEntity)object).getAddress())){
				return true;
			}
		}
		return false;
	}
	
}
