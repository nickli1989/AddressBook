package com.ericsson.ma.javatraining.XmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ericsson.ma.javatraining.AddressBook.AddressController;

public class WriteXml {
    private DocumentBuilder builder;
    private Document document;
    
    private String filename;
    private String configname;
    private String onename;
    private String[] xmlnames;
    
    private static final Logger logger = LoggerFactory.getLogger(WriteXml.class);
    
    public WriteXml(String filename, String configname, String onename, String[] xmlnames){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.filename = filename;
		this.configname = configname;
		this.onename = onename;
		this.xmlnames = xmlnames;
    }
    
    public void write(List<List<String>> list){
    	this.deleteFile();
    	this.document = builder.newDocument();
    	Element root = document.createElement(configname);
    	for(List<String> _list: list){
    		root.appendChild(this.save(_list));
    	}
    	this.document.appendChild(root);
    	this.export();
    	logger.info("Write in xml file success!");
    }
    
    private void export() {
	    TransformerFactory tfactory = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
			transformer = tfactory.newTransformer();
			DOMSource source = new DOMSource(document);
		    transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    OutputStreamWriter pw = new OutputStreamWriter(new FileOutputStream(filename));
		    StreamResult result = new StreamResult(pw);
		    transformer.transform(source, result);
		    pw.close();
		} catch (TransformerConfigurationException e) {
			logger.error("Errors occurred while doing the transform");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Errors by failed or interrupted I/O operations ");
			e.printStackTrace();
		} catch (TransformerException e) {
			logger.error("Errors occurred while doing the transform");
			e.printStackTrace();
		}
	    
	}

	private Node save(List<String> _list) {
		Element root = document.createElement(onename);
		for(int i = 0; i < _list.size(); i++){
			Element element = document.createElement(xmlnames[i]);
			if(_list.get(i) != null){
				element.setTextContent(_list.get(i).toString());			
			}
			root.appendChild(element);
		}
		return root;
	}

	private void deleteFile(){
    	File file = new File(filename);
    	if(file.exists()){
    		file.delete();
    	}
    }
}
