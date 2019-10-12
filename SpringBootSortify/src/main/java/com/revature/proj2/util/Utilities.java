package com.revature.proj2.util;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Revature Proj2: Sortify
 * Package: Util
 * Utilities.java
 * Utilities
 * Purpose: Storage for static variables and methods commonly used in the app
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

public class Utilities {
	//Private variables
	private static HashMap<String, String> dbConfig = new HashMap<>();
	//Private constants
	private final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
	//Public constants
	public final static String USERNAME_PATTERN = "^[a-zA-Z]\\w{5,25}+$";
	public final static String PASSWORD_PATTERN = "^[a-zA-Z]\\w{7,25}+$";
	public final static String SURNAME_PATTERN = "^[a-zA-Z][a-zA-Z\\. ]{0,25}?$";
		
	/**
	 * Get String formatted representation of a Timestamp
	 * 
	 * @param ts Timestamp to be String formatted
	 * @return String String representation of a Timestamp
	 */
	public static String timestampToString(Timestamp ts) {
		return new SimpleDateFormat(TIMESTAMP_PATTERN).format(ts);
	}
	
	/**
	 * Get Timestamp representation of a String
	 * 
	 * @param dateStr String used to create a Timestamp
	 * @return Timestamp representation of a String
	 */
	public static Timestamp stringToTimestamp(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}
	
	/**
	 * Get String value of the db configuration item
	 * 
	 * @param key String key of the db configuration item (url, user, password)
	 * @return String value of the db configuration item
	 */
	public static String getDBConfig(String key) {
		return dbConfig.get(key);
	}
	
	/**
	 * Set up dbConfig map with key-value pairs of the db configuration
	 * 
	 */
	public static void setupDBConfig() {
		try {
			URL url = Utilities.class.getResource("dbconfig.xml");
			File xmlFile = new File(url.getPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        NodeList nodeList = doc.getElementsByTagName("string");
	        for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Element ele = (Element) node;
	        	dbConfig.put(ele.getAttribute("name"), ele.getTextContent());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
