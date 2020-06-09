package com.my.base;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.my.common.ControlCenter;
import com.my.common.Settings;
import com.my.pageObjects.HomePgObj;
import com.my.pageObjects.MenuIconObj;

/*
 * Author: ABacker
 * my
 * 21/08/2019
 *
 */
public class Base extends ControlCenter implements ITestListener 
{
    public static final Logger LOGGER = LogManager.getLogger(Base.class);
   
    

    protected HomePgObj homeob;
    protected MenuIconObj menuob;
    public long startTime;

    protected Settings settings;

	
    
    
    @Override
    @Parameters({"baseUrl","seleniumUrl", "browser"})
    @BeforeMethod

    public void onTestSetup(String baseUrl, String seleniumUrl, String browser, ITestContext ctx, Method method) throws IOException, InterruptedException 
    {
    	LOGGER.info("Setting desired capabilites.");
		startTime = System.currentTimeMillis();
    	PropertyConfigurator.configure("Log4j.properties");
    	
        super.onTestSetup(baseUrl, seleniumUrl, browser, ctx, method);
        settings = new Settings();
       
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);	
    	driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
    	driver.manage().window().maximize();

        homeob = PageFactory.initElements(driver, HomePgObj.class);
        

    }

    public synchronized void loginApp (String user, String pwd) throws InterruptedException {

        homeob = PageFactory.initElements(driver, HomePgObj.class);
        homeob.login(user, pwd);
    }
    
    public synchronized void loginAs(String user) throws InterruptedException, IOException {
    	
        homeob = PageFactory.initElements(driver, HomePgObj.class);
        homeob.loginAs(user);
    }
    
    public synchronized String loginAsRecordOwner() throws InterruptedException, IOException {
    	
        homeob = PageFactory.initElements(driver, HomePgObj.class);
        return homeob.loginAsRecordOwner();
    }
    
    public synchronized void marketoLoginApp (String user, String pwd) throws InterruptedException {

        homeob = PageFactory.initElements(driver, HomePgObj.class);
        homeob.loginToMarketo(user, pwd);
    }

    public synchronized void logout() 
    {
        if (menuob != null) 
        {
            menuob = PageFactory.initElements(driver, MenuIconObj.class);
            menuob.logout();
        }
    }

    public boolean isElementPresent(By by) 
    {
        try {
            driver.findElement(by);
            return true;
        	} 
        catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void captureScreenshot(String fileloc) {
        File screenshot = new File(fileloc);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, screenshot);
        } catch (IOException e) {

            e.printStackTrace();
        }
        ;
    }
    
    

    @AfterMethod
    public void timeCalculator() 
    {
    	 long endTime = System.currentTimeMillis();
    	 long executionTime = endTime - startTime;
    	 
    	 LOGGER.info("TEST EXECUTION TIME:  "+TimeUnit.MILLISECONDS.toMinutes(executionTime)+" Minutes");
        
    }
    
    
    /*			********   EXTEND REPORT CODE   *************
     * 
     * 
     * 
	 * public ExtentHtmlReporter htmlReporter; public ExtentReports extent; public
	 * ExtentTest test;
	 * 
	 * @BeforeTest public void setExtent() { // specify location of the report
	 * htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
	 * "/test-output/ExtendedReport.html");
	 * 
	 * htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of
	 * report htmlReporter.config().setReportName("Regression Testing"); // Name of
	 * the report htmlReporter.config().setTheme(Theme.DARK);
	 * 
	 * extent = new ExtentReports(); extent.attachReporter(htmlReporter);
	 * 
	 * // Passing General information extent.setSystemInfo("Application",
	 * "Salesforce"); extent.setSystemInfo("Environemnt", "DEV SANDBOX");
	 * extent.setSystemInfo("User", "Automation User"); }
	 * 
	 * @AfterTest public void endReport() { extent.flush(); }
	 * 
	 * @AfterMethod public void tearDown(ITestResult result) throws IOException { if
	 * (result.getStatus() == ITestResult.FAILURE) { test.log(Status.FAIL,
	 * "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
	 * test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to
	 * add error/exception in extent report
	 * 
	 * String screenshotPath = Base.getScreenshot(driver, result.getName());
	 * test.addScreenCaptureFromPath(screenshotPath);// adding screen shot } else if
	 * (result.getStatus() == ITestResult.SKIP) { test.log(Status.SKIP,
	 * "Test Case SKIPPED IS " + result.getName()); } else if (result.getStatus() ==
	 * ITestResult.SUCCESS) { test.log(Status.PASS, "Test Case PASSED IS " +
	 * result.getName()); }
	 * 
	 * driver.quit();
	 * 
	 * }
	 * 
	 * 
	 * public static String getScreenshot(WebDriver driver, String screenshotName)
	 * throws IOException { String dateName = new
	 * SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); TakesScreenshot ts =
	 * (TakesScreenshot) driver; File source = ts.getScreenshotAs(OutputType.FILE);
	 * 
	 * // after execution, you could see a folder "FailedTestsScreenshots" under src
	 * folder String destination = System.getProperty("user.dir") + "/Screenshots/"
	 * + screenshotName + dateName + ".png"; File finalDestination = new
	 * File(destination); FileUtils.copyFile(source, finalDestination); return
	 * destination; }
	 * 
	 */
}
