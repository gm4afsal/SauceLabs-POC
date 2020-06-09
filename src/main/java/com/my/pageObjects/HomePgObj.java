package com.my.pageObjects;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.my.common.Settings;

/*
 * Author
 * Afsal Backer
 * 21/08/2019
 *
 */
public class HomePgObj extends Page {
	public static final Logger LOGGER = LogManager.getLogger(HomePgObj.class);

	@Rule
	public ErrorCollector errCol = new ErrorCollector();

	public HomePgObj(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[./@*=//label[contains(text(),'Username')]/@*]")
	private WebElement usrName;

	@FindBy(id = "//input[./@*=//label[contains(text(),'Password')]/@*]")
	private WebElement pwd; 


	@FindBy(id = "username")
	private WebElement usrName2;

	@FindBy(id= "password")
	private WebElement pwd2;

	@FindBy(id = "Login")
	private WebElement loginBtn;

	@FindBy(xpath = "//*[@id='rememberUn']")
	private WebElement remChkBox;

	@FindBy(xpath ="//button[@class='bare branding-userProfile-button slds-button slds-global-actions__avatar slds-global-actions__item-action uiButton forceHeaderButton']")
	private WebElement userIcon;

	@FindBy(xpath = "//*[@id='error']")
	private WebElement logError;
	@FindBy(xpath = "//*[@id='rem']/label")
	private WebElement remUrsNameTxt;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[1]")
	private WebElement myProfileMenu;

	@FindBy(xpath = "//a[@class='profile-link-label logout uiOutputURL']")
	private WebElement signOut;
	
	@FindBy(xpath = "//*[@*='User Detail']")
	private WebElement userDetail;
	
	//Marketo username
	@FindBy(id = "loginUsername")
	private WebElement marketoUsername;
	
	//Marketo password
	@FindBy(id = "loginPassword")
	private WebElement marketoPassword;
		
	//Marketo login button
	@FindBy(id = "loginButton")
	private WebElement marketoLoginButton;

	@FindBy(xpath = "//td[@class='pbButton']/input[@class='btn' and @name='login']")
	private WebElement loginAs;
	
	@FindBy(xpath = "//div[@class='slds-combobox_object-switcher slds-combobox-addon_start forceSearchInputEntitySelector']//input[@class='slds-input slds-combobox__input']")
	private WebElement searchObject;
	
	@FindBy(xpath = "//div[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-dropdown_length-with-icon-10 slds-dropdown_left']")
	private WebElement searchObjectSuggestion;

	
	@FindBy(xpath = "//input[@class='slds-input slds-text-color_default slds-p-left--none slds-size--1-of-1 input default input uiInput uiInputTextForAutocomplete uiInput--{remove}']")
	private WebElement searchBox;
	
	@FindBy(xpath = "//ul[@class='lookup__list  visible']/li")
	private WebElement searchSuggestion;
	
	
	
	
	//Method to login as a different user.
	
	//******* IMP : call verifyLoginAs() every time after calling loginAS() to verify login.
	
	public void loginAs(String userid) throws IOException, InterruptedException
	{
			
		Thread.sleep(2000);
		
			Settings settings = new Settings();
			
			LOGGER.info("Navigate to required user's detail page");
			String baseURL = settings.getSetting("userdetailURL")+userid;
			
			LOGGER.info("URL: "+baseURL);
			driver.get(baseURL);
			pause(2000);
			
				waitForElementVisbility(userDetail);
			
			try 
			{
				userDetail.click();
				pause(3000);
				LOGGER.info("Clicked on user detail");
			} catch (Exception e) {
				Assert.fail("User Detail button not found.");
			}

			try {
				WebDriverWait wait = new WebDriverWait(driver,60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Users'])[2]")));
				LOGGER.info("On user detail page now.");
			} catch (Exception e) {Assert.fail("Unable to open User Detail page.");// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driver.switchTo().frame(0);
			LOGGER.info("Switched to login frame");
			pause(2000);
			
			waitForClickableElement(loginAs);
			loginAs.click();
			pause(4000); 
			LOGGER.info("Clicked on Login");
			
			if(isElementVisible(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")))
			{
				LOGGER.info("Login expired. Clicked on Return to page button.");
				driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")).click();
				Thread.sleep(2000);
				driver.get(driver.getCurrentUrl());
				LOGGER.info("Page Refreshed.");
				Thread.sleep(2000);
			}
			else
			{
				LOGGER.info("Login successful.");
			}
			
			try 
			{
				userIcon.isDisplayed();
			} catch (Exception e) 
			{
				LOGGER.info("Login as functionality failed. Retrying...");
				loginAs.click();
				LOGGER.info("Clicked on Login as.");
				pause(5000);
			}
			
}
	
	
	//Method to login as a record owner.
	
		//******* IMP : call verifyLoginAs() every time after calling loginAS() to verify login.
		
		public String loginAsRecordOwner() throws IOException, InterruptedException
		{
				
				Thread.sleep(2000);
			
				Settings settings = new Settings();
				Page pg = PageFactory.initElements(driver, Page.class);
				
				waitForElementVisbility(userDetail);
				
				
				  //Extract user id from URL 
				  pg.waitForDynamicElement(By.xpath("//span[@class='test-id__section-header-title slds-truncate' and text()='About']"));
				  String userDetailURL = driver.getCurrentUrl();
				  LOGGER.info("At user detail page."); 
				  String[] parts = userDetailURL.split("\\/User/"); 
				  String beforeFirstDot = parts[1];
				  String[] userId = beforeFirstDot.split("\\/view"); 
				  String ownerUserId = userId[0];
				  LOGGER.info("Owner Id is: "+ownerUserId);
				
				try 
				{
					userDetail.click();
					pause(3000);
					LOGGER.info("Clicked on user detail");
				} catch (Exception e) {
					Assert.fail("User Detail button not found.");
				}

				try {
					WebDriverWait wait = new WebDriverWait(driver,60);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Users'])[2]")));
					LOGGER.info("On user detail page now.");
				} catch (Exception e) {Assert.fail("Unable to open User Detail page.");// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.switchTo().frame(0);
				LOGGER.info("Switched to login frame");
				pause(2000);
				loginAs.click();
				pause(4000); 
				LOGGER.info("Clicked on Login");
				
				if(isElementVisible(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")))
				{
					LOGGER.info("Login expired. Clicked on Return to page button.");
					driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")).click();
					Thread.sleep(2000);
					driver.get(driver.getCurrentUrl());
					LOGGER.info("Page Refreshed.");
					Thread.sleep(2000);
				}
				else
				{
					LOGGER.info("Login successful.");
				}
				
				try 
				{
					userIcon.isDisplayed();
				} catch (Exception e) 
				{
					LOGGER.info("Login as functionality failed. Retrying...");
					loginAs.click();
					LOGGER.info("Clicked on Login as.");
					pause(5000);
				}
				
				return ownerUserId;
				
	}
	
	
	//This method is to verify login as a different user is successful.
	public void verifyLoginAs(String userName) throws InterruptedException
	{
		try 
		{	
		driver.findElement(By.xpath("//a[text()='Log out as "+userName+"']")).isDisplayed();
		LOGGER.info("Logged in as: "+userName);
		}
		catch (Exception e) 
		{
			waitForElementVisbility(userIcon);
			userIcon.click();
			LOGGER.info("Clicked on user icon");
			waitForDynamicElement(By.xpath("(//a[@class='profile-link-label' and text()='"+userName+"'])[1]"));
			LOGGER.info("Logged in as: "+userName);
			LOGGER.warn("Verification of logged in user failed.");
		}

	}

	public void login(String user, String passwd)
	{
		
		try {
			Settings settings = new Settings();
			LOGGER.info(user);
			if(isElementVisible(By.id("userNav-arrow")))
			{
				LOGGER.info("Sign out the current log in");
				userIcon.click();
				pause(4000);
				signOut.click();
				pause(3000);
			}
			
			LOGGER.info(driver.getTitle());
			driver.get(settings.getSetting("service.url"));
			pause(1000);
			
			LOGGER.info("I'm ready to enter username and password");
			if (remChkBox.isSelected()){
				remChkBox.click();
				LOGGER.info("Remember me checkbox unselected");
				pause(1000);				
			}
			LOGGER.info("Cursor at the Username field");
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			//Enter UserName
			usrName2.clear();
			usrName2.sendKeys(user);
			LOGGER.info("Username: "+user);
			pwd2.clear();
			pwd2.sendKeys(passwd);
			LOGGER.info("Password: **********");
			loginBtn.click();
			pause(4000); 
			LOGGER.info("Clicked on Login");
			
			if(isElementVisible(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")))
			{
				LOGGER.info("Login expired. Clicked on Return to page button.");
				driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Return to Page']")).click();
				Thread.sleep(2000);
				driver.get(driver.getCurrentUrl());
				LOGGER.info("Page Refreshed.");
				Thread.sleep(2000);
			}
			else
			{
				LOGGER.info("Login successful.");
			}
			
			if(isElementVisible(By.id("error")))
			{
				LOGGER.info("Login failed with an error " + logError.getText());
				
				driver.get(settings.getSetting("service.url"));
				pause(1000);
				LOGGER.info("I'm ready to enter username and password");
				if (remChkBox.isSelected()){
					remChkBox.click();
					LOGGER.info("Remember me checkbox unselected");
					pause(1000);				
				}
				LOGGER.info("Cursor at the Username field");
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
				//Enter UserName
				usrName2.clear();
				usrName2.sendKeys(user);
				LOGGER.info("Username: "+user);
				pwd2.clear();
				pwd2.sendKeys(passwd);
				LOGGER.info("Password: **********");
				loginBtn.click();
				pause(4000); 
				LOGGER.info("Clicked on Login");
				
			}
			
			//Handle notification window
			if(isElementVisible(By.xpath("//span[text()='Scheduled Maintenance']")))
			{
				pause(1000);
				LOGGER.info("Scheduled Maintenance Notification window closed.");
				driver.findElement(By.xpath("//*[@class='continue']")).click();
				pause(2000);
			}
			
			
		} catch (Exception e) 
		{
			Assert.fail("Login attempt failed with an error " + e.getMessage());
		}
	}

	
	public void logOut() throws IOException 
	{
			Settings settings = new Settings();
		
			LOGGER.info("Sign out the current log in");
			userIcon.click();
			pause(3000);
			signOut.click();
			LOGGER.info("Signed out of current login.");
			pause(3000);
			
			
			if(driver.findElements(By.xpath("//label[@class='label usernamelabel']")).size()>0)
			{
				LOGGER.info("Full logout occured. Re-login as Admin.");
				LOGGER.info("Login as Admin");
				login(settings.getSetting("admin.user"), settings.getSetting("admin.password"));
			}
			else 
			{
				LOGGER.info("Logged out of the current user.");
			}
		
	}
	
	public void logOutWithoutAdminLogin() throws IOException 
	{
			Settings settings = new Settings();
		
			LOGGER.info("Sign out the current log in");
			userIcon.click();
			pause(3000);
			signOut.click();
			LOGGER.info("Signed out of current login.");
			pause(3000);
		
		
	}
	
	//Method to login to Marketo
	public void loginToMarketo(String userName, String passWord)
	{
			LOGGER.info("Enter Marketo Username");
			marketoUsername.sendKeys(userName);
			pause(2000);
			
			LOGGER.info("Enter Marketo Password");
			marketoPassword.sendKeys(passWord);
			pause(2000);
			
			LOGGER.info("Click on Submit button.");
			marketoLoginButton.click();
			pause(20000);
			
		
	}
	
	
	//This method is used to navigate to 'All Tabs'
	public void navigateAllTabs(){
		try {
			Settings settings = new Settings();
			String sAllTabURL =settings.getSetting("allTab.url");
			
			driver.get(sAllTabURL);
			pause(2000);
		} catch (Exception e) {
			LOGGER.info("ERRROR - when navigate to All Tabs. " + e.getMessage());
			Assert.fail("ERRROR - when navigate to All Tabs. " + e.getMessage());
		}
	}
	
	//this methds is to click on the option
	public void toClickOnOption(String option){
		try{
			pause(2000);
			LOGGER.info("Option is to click from the All tabs apge");
			//driver.findElement(By.xpath("*//td[2]/a[contains(text(),'"+option+"')]")).click();
			 int i=0;
			 if(i==0) {
			   driver.findElement(By.xpath("*//td[2]/a[contains(text(),'"+option+"')]")).isDisplayed();
				driver.findElement(By.xpath("*//td[2]/a[contains(text(),'"+option+"')]")).click();	
			}
			else 
			{
			driver.findElement(By.xpath("*//td[1]/a[contains(text(),'"+option+"')]")).click();
			}
			pause(2000);
		}catch(Exception e){
			Assert.fail("Not able to find the given option");
		}
	}
	
	//Developers console
	public void developerConsole(String command) throws InterruptedException{
		
		LOGGER.info("To execute the developer console");
		Thread.sleep(5000);

		//Click on Developer Console Link
		try {
			//Click on Developer Console Link on SFDC
			driver.findElement(By.xpath("//*[@id='userNavLabel']")).click();
			Thread.sleep(4000);
		} catch (Exception e1) {
			//Developer Console Link on TourBooking
			driver.findElement(By.xpath("//*[contains(@class, 'icon-settings-bolt')]")).click();
			Thread.sleep(4000);
		}
		
		  String winHandleBefore = driver.getWindowHandle();
		  try{
			  driver.findElement(By.xpath("//*[text()='Developer Console']")).click();
			  Thread.sleep(5000); 
		  }catch(Exception e){
			  LOGGER.info("not able to find the option");
		  }
		  
		Set<String> allWindows = driver.getWindowHandles();
		LOGGER.info("Not able to find the look up icon");
		if (!allWindows.isEmpty()) {
			Integer x = 1;
			for (String windowId: allWindows){

				if (!windowId.equals(winHandleBefore)){
					pause(2000);
					driver.switchTo().window(windowId);
					String title = "Developer Console";
					LOGGER.info("To get the window title:"+driver.getTitle());
					if(driver.getTitle().equalsIgnoreCase(title)){
						LOGGER.info("The title of the newly opened window is:"+driver.getTitle());
						pause(3000);
						
						driver.manage().window().maximize();
						Thread.sleep(4000);
						
						 driver.findElement(By.xpath("//*[text()='File']")).click();
                         driver.findElement(By.xpath("//*[text()='Close All']")).click();
                         driver.findElement(By.xpath("//*[text()='Logs']")).click();
						Thread.sleep(4000);
						driver.findElement(By.xpath("//*[text()='Debug']")).click();
						Thread.sleep(4000);

						driver.findElement(By.xpath("//*[text()='Open Execute Anonymous Window']")).click();
						Thread.sleep(4000);
	
						WebElement web;
						try {
							web = driver.findElement(By.xpath("//*[@class='CodeMirror-code']//pre//span[1]"));
						} catch (Exception e) {
							web = driver.findElement(By.xpath("//*[@class='CodeMirror-code']//pre"));
						}
						
						String CurrentQuery= web.getText();
						
						LOGGER.info("Existing query is" +CurrentQuery);
						Actions actions = new Actions(driver);
						pause(2000);
						actions.moveToElement(web);
						pause(2000);
						actions.click();

						pause(2000);
						
						actions.sendKeys(Keys.HOME);
						LOGGER.info("Cursor on first line");
						actions.sendKeys(command);
						actions.sendKeys(Keys.RETURN);
						
				//added to comment unwanted scripts
						actions.sendKeys("/*");
						actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.DOWN));
						actions.sendKeys("*/");
						Thread.sleep(2000);
						
				//added
						
						actions.build().perform();
						
						LOGGER.info("Entered script");
				
						Thread.sleep(3000);

						driver.findElement(By.xpath("//*[@id='openLogAfterExec-inputEl']")).click();
						LOGGER.info("Log checkbox clicked");
						
						LOGGER.info("Clicking on EXECUTE button");
						
						driver.findElement(By.xpath("//*[@id='openLogAfterExec']/following::*[text()='Execute']")).click();
						Thread.sleep(10000);

						driver.close();
					}
					LOGGER.info("to close the unwanted windows");

				}
				x++;
			}
			driver.switchTo().window(winHandleBefore);
			LOGGER.info("Switching to main window");

		}

	}		 


	//This method is to go to console app from classic
	public void switchtoconsole() 
	{
		
		driver.findElement(By.xpath(".//*[@id='tsidButton']")).click(); //click on admin dropdown
		driver.findElement(By.xpath(".//*[@id='tsid-menuItems']/a[5]")).click(); //click on sales
		LOGGER.info("Switched to Sales console");
		
	}
	
	//Salesforce Global search
	public void globalSearch(String objectName, String searchData) throws InterruptedException
	{
		Page pg = PageFactory.initElements(driver, Page.class);
		
		Thread.sleep(2000);
		LOGGER.info("Search for: "+searchData+" in object "+objectName);
		
		searchObject.clear();
		Thread.sleep(2000);
		searchObject.sendKeys(objectName);
		Thread.sleep(4000);
		LOGGER.info("Entered: "+objectName);
		
		searchObject.sendKeys(Keys.RETURN);
		LOGGER.info("Searching...");
		
		searchBox.sendKeys(searchData);
		Thread.sleep(2000);
		LOGGER.info("Entered: "+searchData);
		
		searchSuggestion.click();
		Thread.sleep(2000);
		LOGGER.info("Clicked on search button.");
		
		
	    //Infinite Loop
		boolean flag = true;
	    while (flag)
	    {   
	        //Verify is "No results is displayed."
			int sizeCatcher = driver.findElements(By.xpath("//h2[@class='slds-m-bottom--xx-small']/a[text()='Leads']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"+searchData+"']")).size();
			LOGGER.info("Search results found: " +sizeCatcher);

	        //Check Entered Number
	        if (sizeCatcher>0)
	        {
	          LOGGER.info("Search results found.");
	          flag=false;
		        LOGGER.info("Open the search result.");
		        
		        pg.waitForClickableDynamicElement(By.xpath("//h2[@class='slds-m-bottom--xx-small']/a[text()='Leads']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"+searchData+"']"));
		        driver.findElement(By.xpath("//h2[@class='slds-m-bottom--xx-small']/a[text()='Leads']/ancestor::div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small']//a[@title='"+searchData+"']")).click();
		        pg.waitForClickableDynamicElement(By.xpath("//lightning-formatted-name[text()='"+searchData+"']"));
		        LOGGER.info("Opened.");
	          break;
	        }
	        else
	        {
	        	LOGGER.info("Searching again...");
	        	
	        	searchBox.clear();
	        	
	        	searchBox.sendKeys(searchData);
	    		Thread.sleep(2000);
	    		LOGGER.info("Entered: "+searchData);
	    		
	    		searchSuggestion.click();
	    		Thread.sleep(4000);
	    		LOGGER.info("Clicked on search button.");
	    		flag=true;
	        }
	        

	    }

		
	}

	
}

