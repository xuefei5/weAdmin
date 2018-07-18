package com.utils;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class SysConfig {
	
	private static final transient Logger logger = Logger.getLogger(SysConfig.class);
	public static Configuration config = null;
	static {
		try {
			logger.info("加载系统配置文件！！");
			config = new PropertiesConfiguration("config/system/system.properties");
		} catch (Exception e) {
		}
	}

	public static String getPropertiesStr(String key) {
		return config.getString(key);
	}

	public static int getPropertiesNum(String key) {
		try {
			return config.getInt(key);
		} catch (Exception e) {
			return -1 ;
		}
		
	}

	public static Boolean getPropertiesBoolean(String key) {
		try {
			return config.getBoolean(key);
		} catch (Exception e) {
			return false ;
		}
	}
	
	/*获取配置文件key列表*/
	public static List<String> getPropertiesKeyList(String propertiesUrl) throws ConfigurationException{
		List<String> keyList = new ArrayList<String>();
	
		Configuration config = null;
		config = new PropertiesConfiguration(propertiesUrl);
		Iterator<String> keys = config.getKeys();
		while(keys.hasNext()) {
			keyList.add(keys.next());
		}
		
		return keyList;
	}
	
	/*获取配置文件value列表*/
	public static List<String> getPropertiesValueList(String propertiesUrl) throws IOException{
		List<String> valueList = new ArrayList<String>();
		
		//获取配置文件并加载
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(propertiesUrl));
		pps.load(in);
		
		//得到配置文件的名字
		Enumeration en = pps.propertyNames();
		while(en.hasMoreElements()) {			
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			valueList.add(strValue);
		}
		return valueList;
	}
	
	public static void main(String[] args) {
		String tableName = getPropertiesStr("server_ip");// URL
		System.out.println("============" + tableName);
	}
}
