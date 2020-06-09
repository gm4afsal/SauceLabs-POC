package com.my.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSetup
{
    public WebDriver driver;
    private String testName;
    
    public WebDriverSetup() 
    {
        this.testName = "";
    }
    
    public WebDriverSetup(final String testName) 
    {
        this.testName = "";
        this.testName = testName;
    }
    
    public WebDriver getDriver() 
    {
        return this.driver;
    }
    
    public WebDriver initialize(final String baseUrl, final String seleniumUrl, final String browserUnderTest) {
        return this.initialize(baseUrl, seleniumUrl, browserUnderTest, "");
    }
    
    public WebDriver initialize(final String baseUrl, final String seleniumUrl, final String browserUnderTest, final String preferedArchitecture) 
    {
    	
    	System.out.println("In WebDriver Initialize Method.");   
       
        final String browserName = browserUnderTest.toLowerCase();
        switch (browserName) 
        {
            case "firefox": 
            {
            	System.out.println("Browser: Firefox.");
            	//System.setProperty("webdriver.gecko.driver", "./drivers/firefox/geckodriver");
            	
            	WebDriverManager.firefoxdriver().setup();
            	
            	driver = new FirefoxDriver();
           
        		break;
        		
            }
            
            case "chrome": 
            {
            	
              	System.out.println("Browser: Chrome");
            	
              	System.setProperty("webdriver.chrome.driver", "./drivers/chrome/mac_chromedriver");
            	
              	WebDriverManager.chromedriver().version("79.0.3945.36").setup();
              	
            	ChromeOptions options = new ChromeOptions();
            	
            	//options.addArguments("--headless");
            	//options.addArguments("--window-size=1920,1080");
            	
            	options.addArguments("--disable-extensions");
            	options.addArguments("--ignore-certificate-errors");
            	options.addArguments("--disable-infobars");
             
                driver = new ChromeDriver(options);
                
                driver.get(baseUrl);

            	break;
            }
            
        }
       
        return this.driver;
    }
    
//    public void setSystemProps(final String baseUrl, final String seleniumUrl, final int timeout, final String browserUnderTest, final boolean grid) 
//    {
//      
//    	System.out.println("Setting System Properties.");
//
//        
//    }
    
    
}