package com.my.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;

import com.my.base.Base;

  /*
   * Author : ABACKER
   * my
   * 21/08/2019
   * 
   */
public class Settings {
	public static final Logger LOGGER = LogManager.getLogger(Settings.class);

	private Properties prop = new Properties();
	
	public Settings() throws IOException 
	{
		
		prop.load(this.getClass().getClassLoader().getResourceAsStream("configDEV.properties"));
		
	}
	
	
	public String getDriverLocationFF() {
		return prop.getProperty("drivers.ff");
	}
	public String getDriverLocationIE() {
		return prop.getProperty("drivers.ie");
	}
	
	public String getDriverLocationChrome() {
		return prop.getProperty("drivers.chrome");
	}
	
	public String getDriverLocationSF() {
		return prop.getProperty("drivers.SF");
	}
	
	public String getServiceUrl() {
		return prop.getProperty("service.url");
	}
	
	public String getAdminUser() {
		return prop.getProperty("admin.user");
	}
	
	public String getAdminPassword() {
		return prop.getProperty("admin.password");
	}
	
	public String getMarketoUser() {
		return prop.getProperty("marketoadmin.user");
	}
	
	public String getMarketoPassword() {
		return prop.getProperty("marketoadmin.password");
	}
	
	public String getUser() {
		return prop.getProperty("regular.user");
	}
	
	public String getPassword() {
		return prop.getProperty("regular.password");
	}
	
	public String getNormalAltPassword() {
		return prop.getProperty("regular.altpassword");
	}
	
	
	public String getSetting(String property) {
		
		return prop.getProperty(property);
	}
	
}
