package com.herokuapp.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	public Properties prop;
	
	public ReadConfig() {
		
		String filePath = "C:\\Users\\user\\eclipse-workspace\\herokuapp\\configuration\\config.properties";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getURL() {
		String url = prop.getProperty("url");
		return url;
	}
	
	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}

}
