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


public class sauceLabs2 extends Base
{
	

	 @Test
	 public void openGoogle() throws InterruptedException					   
	 {	
		
		 //LOGIN AS ADMIN		
		 LOGGER.info("Open Google.");
		 driver.get("https://www.google.com");
		 Thread.sleep(2000);
		 
		 LOGGER.info("Google opened.");
		 
	}
	 
	 @Test
	 public void openAmazon() throws InterruptedException					   
	 {	
		
		 //LOGIN AS ADMIN		
		 LOGGER.info("Open Amazon.");
		 driver.get("https://www.amazon.com");
		 Thread.sleep(2000);
		 
		 LOGGER.info("Google Amazon.");
		 
	}
}
