package com.my.saucelabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.my.base.Base;
import com.my.pageObjects.HomePgObj;


public class sauceLabs1 extends Base
{
	

	 @Test
	 public void openGmail() throws InterruptedException					   
	 {	
		
		 //LOGIN AS ADMIN		
		 LOGGER.info("Open Gmail.");
		 driver.get("https://www.gmail.com");
		 Thread.sleep(5000);
		 
		 LOGGER.info("Gmail opened.");
		 
	}
	 
}
