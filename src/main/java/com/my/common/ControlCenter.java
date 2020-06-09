package com.my.common;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.io.IOException;
import com.my.common.WebDriverSetup;
import com.my.miscJobs.VoneUpdater;

import java.lang.reflect.Method;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class ControlCenter implements ITestListener
{
   
    public WebDriver driver;
    
    @Parameters({ "baseUrl", "seleniumUrl", "browser" })
    @BeforeMethod
    public void onTestSetup(final String baseUrl, final String seleniumUrl, final String browser, final ITestContext ctx, final Method method) throws IOException, InterruptedException {
        final String xmlTestName = ctx.getName();
        final String methodName = method.getName();
        final String testName = xmlTestName + "." + methodName;
        System.out.println("Test Name is: " + testName);
        
        final WebDriverSetup setup = new WebDriverSetup(testName);
        this.driver = setup.initialize(baseUrl, seleniumUrl, browser);
 
        
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown() 
    {
    	System.out.println("Close Browser."); 
    	if (this.driver != null) 
    	{
    		System.out.println("Close Browser"); 
    		this.driver.quit();
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) 
    {
       
		VoneUpdater vo = PageFactory.initElements(driver, VoneUpdater.class);
		System.out.println("Test Failed. Updating Vone status...");
		
		System.out.println("Story ID is:  " + result.getTestContext().getAttribute("storyAttribute"));
		System.out.println("Test ID is:  " + result.getTestContext().getAttribute("testAttribute"));
		
		vo.updateVoneFail(result.getTestContext().getAttribute("testAttribute"),result.getTestContext().getAttribute("storyAttribute"));
        
    }


	@Override
	public void onTestSuccess(ITestResult result) 
	{
		VoneUpdater vo = PageFactory.initElements(driver, VoneUpdater.class);
		System.out.println("Test Success. Updating Vone status...");
		
		System.out.println("Story ID is:  " + result.getTestContext().getAttribute("storyAttribute"));
		System.out.println("Test ID is:  " + result.getTestContext().getAttribute("testAttribute"));
		
		vo.updateVonePass(result.getTestContext().getAttribute("testAttribute"),result.getTestContext().getAttribute("storyAttribute"));
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
   
}