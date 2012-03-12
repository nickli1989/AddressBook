package com.ericsson.ma.javatraining.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ericsson.ma.javatraining.AddressBook.AddressEntity;

public class ReadXml {
    private List<List<String>> list;
    private DocumentBuilder builder;
    private Document document;
    
    private static final Logger logger = LoggerFactory.getLogger(ReadXml.class);
    
    public ReadXml(){
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
    }
    
    public List<List<String>> read(String filename, String onename, String[] xmlNames){
    	File file = new File(filename);
    	if(!file.exists()){
    		logger.info("ReadXml failed! No such xml file.");
    		return new ArrayList<List<String>>();
    	}
    	try {
			document = builder.parse(file);
		} catch (SAXException e) {
			logger.error("Xml parse error!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Errors by failed or interrupted I/O operations ");
			e.printStackTrace();
		}
		list = new ArrayList<List<String>>();
		NodeList nodeList = document.getElementsByTagName(onename);
		List<String> newlist;
		for(int i = 0; i < nodeList.getLength(); i++){
			newlist = new ArrayList<String>();
			for(int j = 0; j < xmlNames.length; j++){
				newlist.add(document.getElementsByTagName(xmlNames[j]).item(i).getTextContent());
			}
			list.add(newlist);
		}
		logger.info("Read xml success!");
		return this.list;
    }
}
