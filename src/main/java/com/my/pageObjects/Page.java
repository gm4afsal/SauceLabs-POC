package com.my.pageObjects;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;
import com.my.common.Settings;
/*
 * Author
 * Afsal Backer
 * 8/22/2019
 * 
 */
public class Page {
	public static final Logger LOGGER = LogManager.getLogger(Page.class);

	WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}

	public void deleteAction (){
		Actions action = new Actions (driver);
		action.sendKeys(Keys.DELETE).perform();

	}

	public void enterAction (){
		Actions action = new Actions (driver);
		action.sendKeys(Keys.ENTER).perform();

	}
	public void pause(long time) {
		try {
			Thread.sleep(time);

		} catch (Exception e) {
		}
	}   
	public void selectDropdown(WebElement element, String text){
		Select dropDownBox = new Select(element);
		dropDownBox.selectByVisibleText(text);
	}


	public String currentDate(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public String currentDateTime(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy h:mm a"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	//This method is used to get a Current Date but on this Format YYYY/M/dd
	public String currentDateYearMonthDay(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	//This method subtract days to get a past Date, Format M/D/YYYY
	public String pastDateDateYearMonthDayFormat(int subtractDays){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, -subtractDays);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");//format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	//This method is used to get a Current Date but on this Format M/D/YYYY
	public String currentDateSimpleDate(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	public String futureDate(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, 1);
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public String futureDateTime(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, 1);
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy h:mm a"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	//This method is used to get a Future Date but on this Format M/D/YYYY
	public String futureDateSimpleFormat(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, 1);
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	//This method add days to get a Future Date, Format M/D/YYYY
	public String futureDateSimpleFormat(int addDays){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, addDays);
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	//This method add days to get a Future Date, Format MMM d, yyyy
	public String futureDateBookFormat(int addDays){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, addDays);
		SimpleDateFormat formatter= new SimpleDateFormat("MMM d, yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}


	//This method subtract days to get a past Date, Format M/d/yyyy h:mm a
	public String pastDateTime(int subtractDays){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, -subtractDays);
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy h:mm a"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	//This method subtract days to get a past Date, Format M/D/YYYY
	public String pastDateSimpleFormat(int subtractDays){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, -subtractDays);
		SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
	
	public String currentDatePlusThreeDays(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		currentDate.add(Calendar.DATE, 3);
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	public String subGrpUnique(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	public String subGrpUnique_dayHrMin(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("dd HH:mm"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	public String subGrpUnique_HrMin(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("HHmmss"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	public String subGrpUnique_YrMonthDateHrMinSec(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyMMddHHmmss"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	public String subGrpUnique_monthdate(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("MM-dd HH:mm:ss"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		dateTime = dateTime.replace("-", "");   //Added this since on GUest only are allowed 15 characters
		dateTime = dateTime.replace(":", "");
		dateTime = dateTime.replace(" ", "");
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	public String monthTime(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yy-MM-dd HH:mm:ss"); //format it as per your requirement
		String dateTime = formatter.format(currentDate.getTime());
		//LOGGER.info("Date and time: "+ dateTime);
		return dateTime;
	}

	//This method format the Date in DD/MM/YYYY
	public String formatDateTwoDigits(String pDate){
		String sFormattedDate = "";

		//Split Month Day and Year
		for (String retval: pDate.split("/", 3)){

			//Add zero if only one digit is displayed
			if((retval.length()) == 1){
				retval = "0" + retval;
			}
			sFormattedDate = sFormattedDate + retval + "/";
		}

		//Return the formatted Date
		sFormattedDate = sFormattedDate.substring(0, 10);
		return sFormattedDate;
	}

	//This method Adding some minutes to a given DateTime (Used on TRransportation Dispatch)
	public String formatDateTimeMinutesAfter(String pDate, Integer pMinutesAfter) throws ParseException{	

		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy hh:mm aa");
		Date date = sdf.parse(pDate); 
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.MINUTE, pMinutesAfter);// Add 4 Minutes to the given DateTime
		String sDateTimeAfter = sdf.format(cal.getTime());

		return sDateTimeAfter;
	}

	//This method Adding some minutes to a given DateTime (Used on TRransportation Dispatch)
		public String formatDateTimeMinutesBefore(String pDate, Integer pMinutesBefore) throws ParseException{	

			SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy hh:mm aa");
			Date date = sdf.parse(pDate); 
			Calendar cal = sdf.getCalendar();
			cal.add(Calendar.MINUTE, -pMinutesBefore);// Add 4 Minutes to the given DateTime
			String sDateTimeBefore = sdf.format(cal.getTime());

			return sDateTimeBefore;
		}
	public String formatDateTimeHoursAfter(String pDate, Integer pHoursAfter) throws ParseException{	
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy hh:mm aa");
		Date date = sdf.parse(pDate); 
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.HOUR, pHoursAfter);// Add 4 Minutes to the given DateTime
		String sDateTimeAfter = sdf.format(cal.getTime());
		return sDateTimeAfter;
	}


	//Generate name and email for a guest
	public String[] generateNameAndEmail() throws IOException{
		
		Settings settings = new Settings();
		String time = subGrpUnique_monthdate();
		String[] guestDetails = new String[2];
		
		String gfirstName =settings.getSetting("guest.firstName");
		String gsecondName =settings.getSetting("guest.lastName");
		String sEmail =settings.getSetting("guest.email2");

		guestDetails[0] =  gfirstName+time + " " + gsecondName+time;
		guestDetails[1] = time + sEmail;
		
		return guestDetails;
	}
	
	
	public void switchToAnotherFrame(WebElement target) throws InterruptedException{		
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.switchTo().frame(target);
	}

	//This method Switch to a Frame by its Name
	public void switchToFrameByName(String pName) throws InterruptedException
	{
		try {
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			driver.switchTo().frame(pName);
		} catch (Exception e) {
			LOGGER.info("Unable to switch to frame");
		}
	}

	public WebDriver waitForElementVisbility(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		LOGGER.info("Element visible.");
		return (driver);
	}
	
	public WebDriver waitForDynamicElement(By element) throws InterruptedException{
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		LOGGER.info("Element found.");
		return (driver);
	}
	
	public WebDriver waitForClickableDynamicElement(By element) throws InterruptedException{
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		LOGGER.info("Element found.");
		return (driver);
	}
	
	public WebDriver waitForClickableElement(WebElement element) throws InterruptedException{
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		LOGGER.info("Element clickable.");
		return (driver);
	}
	
	
	public WebDriver waitForElementToLoad(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
		return (driver);
	}

	public WebElement getWhenVisible(By locator, int timeout) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public WebDriver waitForElementToBeInvisble(By element) throws InterruptedException
	{
		Thread.sleep(2000);
		long timeoutInSeconds = 1000000000;
	    new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(element));
		LOGGER.info("Element invisible.");
		return (driver);
	}
	
	public void lightningPencilEdit(String fieldName) throws InterruptedException
	{
			Thread.sleep(2000);		;
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath("//button[contains(@class,'test-id__inline-edit-trigger') and @title='"+fieldName+"']"));
			actions.doubleClick(elementLocator).perform();
			LOGGER.info("Clicked on Pencil Edit icon.");
			Thread.sleep(2000);
			waitForElementToBeInvisble(By.xpath("//button[contains(@class,'test-id__inline-edit-trigger') and @title='"+fieldName+"']"));
			LOGGER.info("In Edit mode now.");
		
	}
	
	//Method to wait for save complete after editing any object details
	public void lightningPencilEditSaveComplete(String fieldName) throws InterruptedException
	{
		Thread.sleep(2000);	
		WebElement elementLocator = driver.findElement(By.xpath("//button[contains(@class,'test-id__inline-edit-trigger') and @title='"+fieldName+"']"));
		waitForClickableElement(elementLocator);
		LOGGER.info("Saved.");
		Thread.sleep(2000);	
	}

	public void globalSearch(String search) throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='phSearchInput']")).sendKeys(search);
		driver.findElement(By.xpath("//*[@id='phSearchButton']")).click();		
		pause(2000);
	}

	public void globalSearchClick(String stringToSearch)throws InterruptedException {
		LOGGER.info("Doing Global Search: "+ stringToSearch);
		
		try {
			driver.findElement(By.xpath("//*[@id='phSearchInput']")).sendKeys(" ");
		} catch (Exception e2) {
			
		}

		navigateAllTabs();
	
		// Search for tour Name from the Global search
		globalSearch(stringToSearch);

		try {
						
			try {
				// looking for the element present after the search
				WebElement toSearch = driver.findElement(By.xpath("//*[(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + stringToSearch.toLowerCase() + "')]"));
				toSearch.isDisplayed();				
				// To click on the searched record
				toSearch.click();	
			
			} catch (Exception e1) {
				driver.findElement(By.xpath("//*[@id='searchAllSummaryView']")).click();
				// looking for the element present after the search
				WebElement toSearch = driver.findElement(By.xpath("//*[(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + stringToSearch.toLowerCase() + "')]"));
				toSearch.isDisplayed();
				// To click on the searched record
				toSearch.click();	
			}
		
			pause(3000);

		} catch (Exception e) {
			LOGGER.info("The record is not displayed on the UI " + e.getMessage());
			Assert.fail("The record is not displayed on the UI " + e.getMessage());
		}
	}
	
	public void globalSearchClickGroup(String stringToSearch)throws InterruptedException {
		LOGGER.info("Doing Global Search: "+ stringToSearch);
		// Search for tour Name from the Global search
		globalSearch(stringToSearch);

		try {
						
			try {
				// looking for the element present after the search
				WebElement toSearch = driver.findElement(By.xpath("//*[(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + stringToSearch.toLowerCase() + "')]"));
				toSearch.isDisplayed();				
				// To click on the searched record
				toSearch.click();
			} catch (Exception e1) {
				driver.findElement(By.xpath("//*[@id='searchAllSummaryView']")).click();
				// looking for the element present after the search
				WebElement toSearch = driver.findElement(By.xpath("//*[(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + stringToSearch.toLowerCase() + "')]"));
				toSearch.isDisplayed();
				// To click on the searched record
				toSearch.click();
			}
		
			pause(3000);

		} catch (Exception e) {
			LOGGER.info("The record is not displayed on the UI " + e.getMessage());
			Assert.fail("The record is not displayed on the UI " + e.getMessage());
		}
	}


	public void navigateAllTabs(){
		try {
			pause(1000);

			//Process to determine if the User is logged to SFDC / DVC COnsole
			if (driver.getCurrentUrl().indexOf("/console") == -1)
			{
				//SFDC is Displayed (Click on All Tab)
				LOGGER.info("User not in console view. Accessing all tabs button...");
				try 
				{
					driver.findElement(By.xpath("//*[@title='All Tabs']")).click();
					pause(5000);
				} catch (Exception e){
					Settings settings = new Settings();
					String sAllTabURL =settings.getSetting("allTab.url");

					//DVC Console is Displayed (Navigate to "All" tab by URL)
					LOGGER.info("Accessing all tabs via URL...");
					driver.get(sAllTabURL);
					pause(2000);
				}
					
			}
				
			else{
				
				Settings settings = new Settings();
				String sAllTabURL =settings.getSetting("allTab.url");

				//DVC Console is Displayed (Navigate to "All" tab by URL)
				LOGGER.info("Accessing all tabs via URL...");
				driver.get(sAllTabURL);
				pause(2000);
			} 
		}catch (Exception e) {
			System.err.println("Error when try to access all tabs");
			Assert.fail("Error when try to access all tabs");
		}	
	}

	//This Method clicks on 'Back to Sales'  (Once we navigate to All Tabs)
	public void backToSales(){
		try {
			WebElement oBackSales = driver.findElement(By.xpath("//a[text() = 'Back to Sales']"));
			oBackSales.click();
			pause(3000);
			LOGGER.info("Click on 'Back to Sales'");
		} catch (Exception e) {
			LOGGER.info("ERROR when click on 'Back to Sales'");
			Assert.fail("ERROR when click on 'Back to Sales'");
		}
	}

	//This Method clicks on 'Back to Sales'  (Once we navigates to All Tabs)
	public void backTo(String pOption){
		try {
			WebElement oBackTo = driver.findElement(By.xpath("//a[text() = '" + pOption +"']"));
			oBackTo.click();
			pause(3000);
			LOGGER.info("Click on '" + pOption +"'");
		} catch (Exception e) {
			LOGGER.info("ERROR when click on '" + pOption +"'");
			Assert.fail("ERROR when click on '" + pOption +"'");
		}
	}

	

	//This Method navigates to specific Application available on 'App Launcher'
	public void appLauncher(String sApp){


		try {
			pause(3000);
			driver.findElement(By.xpath("//input[@placeholder = 'Start typing']")).isDisplayed();
			LOGGER.info("Ready to enter input");

			
		} catch (Exception e1) {
			LOGGER.info("Navigating to all apps");
		}

		try {
			//Click on 'App Launcher' Icon
			WebDriverWait wait = new WebDriverWait(driver, 30); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'App Launcher')]")));
			
			driver.findElement(By.xpath("//button[contains(.,'App Launcher')]")).click();
			LOGGER.info("Opened App Launcher");
			pause(3000);
			
		} catch (Exception e) 
		{
			Assert.fail("ERROR on App Launcher when it tries to navigate to '" + sApp + "'.");
		}
			
			try {
				driver.findElement(By.xpath("//button[text()='View All']")).click();
				LOGGER.info("Clicked on view all.");
				WebElement oFindApp = driver.findElement(By.xpath("//input[@placeholder = 'Search apps or items...']"));
				oFindApp.sendKeys(sApp);
				pause(1000);
				LOGGER.info("Entered: "+sApp);
			} catch (Exception e) 
			{
				Assert.fail("Unable to enter:  '" + sApp + "'.");
			}


			try 
			{
				WebElement oApp = driver.findElement(By.xpath("//p[@class='slds-truncate']//mark[text()= '"+sApp+"']"));
				oApp.click();
				pause(5000);
			} catch (Exception e) {
				Assert.fail("Unable to click:  '" + sApp + "'.");
			}
			
			LOGGER.info("Clicked on: "+sApp);

			LOGGER.info("User navigated to '" + sApp + "'.");

		

	}


	//This method validate that user cannot navigate to specific Application available on 'App Launcher'
	public void closeAppLauncher(){
		try {
			//Click on 'Close this window' Icon
			driver.findElement(By.xpath("//button[contains(@class,'closeIcon')]")).click();
			pause(3000);

		} catch (Exception e) {
			LOGGER.info("ERROR on App Launcher when it tries to close it.");
			Assert.fail("ERROR on App Launcher when it tries to close it.");
		}
	}

	/**
	 * Multiple select Picker
	 * 
	 * @param listName - Name of the select Picker in GUI
	 * @param values - values need to be  choose or unchoose
	 * @param addRemove - choose or unchoose
	 */
	public void multipleSelectPickList(String listName, List<String> values, boolean addRemove){		

		Select dropDownAvialableBox = new Select(driver.findElement(By.xpath("//select[@id=concat(string(//label[contains(text(),'"+listName+"')]/@for) , '_unselected' )]")));
		Select dropDownChosenBox = new Select(driver.findElement(By.xpath("//select[@id=concat(string(//label[contains(text(),'"+listName+"')]/@for) , '_selected' )]")));
		WebElement buttonRemove = driver.findElement(By.xpath("//img[@id=concat(string(//label[contains(text(),'"+listName+"')]/@for) , '_left_arrow' )]/.."));
		WebElement buttonAdd= driver.findElement(By.xpath("//img[@id=concat(string(//label[contains(text(),'"+listName+"')]/@for) , '_right_arrow' )]/.."));
		List<String> actualValuetoAdd = new ArrayList<String>();
		boolean valueFound = false;
		List<String> actualValuetoRemove = new ArrayList<String>();

		for(String value : values){
			if(addRemove){
				for(WebElement dropValue: dropDownAvialableBox.getOptions()){
					if(dropValue.getText().equals(value)){
						actualValuetoAdd.add(value);				
					}
				}
				if(!actualValuetoAdd.contains(value)){
					valueFound =false;
					for(WebElement dropValue: dropDownChosenBox.getOptions()){
						if(dropValue.getText().equals(value)){
							valueFound = true;
							LOGGER.info("Value " + value + " already chosen for " + listName);
						}
					}
					if(!valueFound){
						LOGGER.info("Value " + value + " in valid for list " + listName);						
						Assert.fail("Value " + value + " in valid for list " + listName);

					}
				}
			}
			else{
				for(WebElement dropValue: dropDownChosenBox.getOptions()){
					if(dropValue.getText().equals(value)){
						actualValuetoRemove.add(value);				
					}
				}
				if(!actualValuetoRemove.contains(value)){
					valueFound =false;
					for(WebElement dropValue: dropDownAvialableBox.getOptions()){
						if(dropValue.getText().equals(value)){
							valueFound = true;
							LOGGER.info("Value " + value + " already unchosen for " + listName);
						}
					}
					if(!valueFound){
						LOGGER.info("Value " + value + " in valid for list " + listName);						
						Assert.fail("Value " + value + " in valid for list " + listName);

					}
				}

			}
		}

		if(addRemove){
			for(String value : actualValuetoAdd){
				dropDownAvialableBox.selectByVisibleText(value);
				pause(1000);
				buttonAdd.click();
				pause(1000);
				LOGGER.info("Value " + value + " chosen for " + listName);
			}
		}
		else{
			for(String value : actualValuetoRemove){
				dropDownChosenBox.selectByVisibleText(value);
				pause(1000);
				buttonRemove.click();
				pause(1000);
				LOGGER.info("Value " + value + " unchosen for " + listName);
			}
		}

	}

	/**
	 * Multiple select Picker - receive the xpath from the add and remove lists
	 * 
	 * @param listName - Name of the select Picker in GUI
	 * @param values - values need to be  choose or unchoose
	 * @param addRemove - choose or unchoose
	 */
	public void multipleSelectPickList(String xpathUnselected, String xpathSelected, List<String> values, boolean addRemove){		

		Select dropDownAvialableBox = new Select(driver.findElement(By.xpath(xpathUnselected)));
		Select dropDownChosenBox = new Select(driver.findElement(By.xpath(xpathSelected)));
		WebElement buttonRemove = driver.findElement(By.xpath("//*[@alt='Remove']"));
		WebElement buttonAdd= driver.findElement(By.xpath("//*[@alt='Add']"));
		List<String> actualValuetoAdd = new ArrayList<String>();
		boolean valueFound = false;
		List<String> actualValuetoRemove = new ArrayList<String>();

		for(String value : values){
			if(addRemove){
				for(WebElement dropValue: dropDownAvialableBox.getOptions()){
					if(dropValue.getText().equals(value)){
						actualValuetoAdd.add(value);				
					}
				}
				if(!actualValuetoAdd.contains(value)){
					valueFound =false;
					for(WebElement dropValue: dropDownChosenBox.getOptions()){
						if(dropValue.getText().equals(value)){
							valueFound = true;
							LOGGER.info("Value " + value + " already chosen from the expected list.");
						}
					}
					if(!valueFound){
						LOGGER.info("Value " + value + " in valid for list " );						
						Assert.fail("Value " + value + " in valid for list " );

					}
				}
			}
			else{
				for(WebElement dropValue: dropDownChosenBox.getOptions()){
					if(dropValue.getText().equals(value)){
						actualValuetoRemove.add(value);				
					}
				}
				if(!actualValuetoRemove.contains(value)){
					valueFound =false;
					for(WebElement dropValue: dropDownAvialableBox.getOptions()){
						if(dropValue.getText().equals(value)){
							valueFound = true;
							LOGGER.info("Value " + value + " already unchosen for " );
						}
					}
					if(!valueFound){
						LOGGER.info("Value " + value + " in valid for list " );						
						Assert.fail("Value " + value + " in valid for list " );

					}
				}

			}
		}

		if(addRemove){
			for(String value : actualValuetoAdd){
				dropDownAvialableBox.selectByVisibleText(value);
				pause(1000);
				buttonAdd.click();
				pause(1000);
				LOGGER.info("Value " + value + " chosen for " );
			}
		}
		else{
			for(String value : actualValuetoRemove){
				dropDownChosenBox.selectByVisibleText(value);
				pause(1000);
				buttonRemove.click();
				pause(1000);
				LOGGER.info("Value " + value + " unchosen for ");
			}
		}

	}

	
	/**
	 * Validate options in a drop-down list
	 * 
	 * @param expected - List of values expected in the list
	 * @param xpath - xpath to identify the drop-down list in the screen
	 */
	public void checkOptions(String[] expected, String xpath){

		List<WebElement> options = driver.findElements(By.xpath(xpath));
		boolean flag = true;
		int k = 0;

		for (WebElement opt : options){

			if (!opt.getText().equals(expected[k])){
				flag = false;
			}

			k = k + 1;
		}

		if (!flag){
			Assert.fail("Not all the segment options are displayed in the pick list values.");
		}else{
			LOGGER.info("Successful validation of the options in the drop-down list.");
		}
	}

	public boolean isElementVisible(By locator) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	    boolean result = ExpectedConditions.invisibilityOfElementLocated(locator).apply(driver);
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
	    return !result;
	}
	
	public boolean compareElements(String a,List<WebElement> b) {
	    for (WebElement we:b) {
	        if (a.equalsIgnoreCase(we.getText())) {
	            return true;
	        }
	    }    
	    return false;
	}
	
	/**
	 * This method create a view without select columns to displayed
	 * @param pField
	 * @param pOperator
	 * @param pValue
	 * @return
	 */
	public String createViewWithoutListValues(String pField, String pOperator, String pValue){

		String viewName = null; 

		try {

			LOGGER.info("Creat new view");
			driver.findElement(By.xpath("//*[text()='Create New View']")).click();
			pause(3000);

			viewName = "SIT Test_" + subGrpUnique_monthdate();
			driver.findElement(By.id("fname")).sendKeys(viewName);
			pause(2000);

			selectDropdown(driver.findElement(By.id("fcol1")),pField);
			selectDropdown(driver.findElement(By.id("fop1")),pOperator);
			driver.findElement(By.id("fval1")).sendKeys(pValue);
			pause(1000);

			driver.findElement(By.xpath("(//*[@*='Save'])[1]")).click();
			LOGGER.info("View created. Name:" + viewName );

		} catch (Exception e) {
			LOGGER.info("ERROR - Not able to create new view. " + e.getMessage());
			Assert.fail("ERROR - Not able to create new view. " + e.getMessage());
		}

		return viewName;
	}


	/**
	 * This method create a view without select columns to displayed
	 * @param pField
	 * @param pOperator
	 * @param pValue
	 * @return
	 */
	public void createViewWithListValuesAndOneFieldFilter(String viewName, String pField, String pOperator, String pValue,  List<String> columnValues){

		try {

			LOGGER.info("Creat new view");
			driver.findElement(By.xpath("//*[text()='Create New View']")).click();
			pause(3000);

			driver.findElement(By.id("fname")).sendKeys(viewName);
			pause(2000);

			selectDropdown(driver.findElement(By.id("fcol1")),pField);
			selectDropdown(driver.findElement(By.id("fop1")),pOperator);
			driver.findElement(By.id("fval1")).sendKeys(pValue);
			pause(1000);

			
			Select dropDownAvialableBox = new Select(driver.findElement(By.xpath("//select[@id='colselector_select_0']")));
			Select dropDownChosenBox = new Select(driver.findElement(By.xpath("//select[@id='colselector_select_1']")));
			WebElement buttonRemove = driver.findElement(By.xpath("//img[@class='leftArrowIcon']"));
			WebElement buttonAdd= driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
			List<String> actualValuetoAdd = new ArrayList<String>();
			boolean valueFound = false;
			
			for(String value : columnValues){

				for(WebElement dropValue: dropDownAvialableBox.getOptions()){
					LOGGER.info(dropValue.getText());
					LOGGER.info(value);
					if(dropValue.getText().equals(value)){
						actualValuetoAdd.add(value);				
					}
				}

				if(!actualValuetoAdd.contains(value)){
					valueFound =false;
					for(WebElement dropValue: dropDownChosenBox.getOptions()){
						LOGGER.info(dropValue.getText());
						if(dropValue.getText().equals(value)){
							valueFound = true;
							LOGGER.info("Value " + value + " already chosen");
						}
					}
					if(!valueFound){
						LOGGER.info("Value " + value + " in valid for list ");						
						Assert.fail("Value " + value + " in valid for list " );

					}
				}
			}
			
			for(String value : actualValuetoAdd){
				dropDownAvialableBox.selectByVisibleText(value);
				pause(1000);
				buttonAdd.click();
				pause(1000);
				LOGGER.info("Value " + value + " chosen for list. " );
			}
			
			//////

			driver.findElement(By.xpath("(//*[@*='Save'])[1]")).click();
			LOGGER.info("View created. Name:" + viewName );

		} catch (Exception e) {
			LOGGER.info("ERROR - Not able to create new view. " + e.getMessage());
			Assert.fail("ERROR - Not able to create new view. " + e.getMessage());
		}
	}
	
	/**
	 * This method select the given view and edit the filters, if this does not exist the view is created with the given parameters
	 * @param pViewName
	 * @param pFilterFieldName
	 * @param pOperator
	 * @param pValue
	 * @param columnValues
	 */
	public void editView(String pViewName, String pField, String pOperator, String pValue, List<String> columnValues){
	
		try {
			driver.findElement(By.xpath("//select[@id='fcf']/option[text()='" + pViewName +"']")).isDisplayed();
			selectDropdown(driver.findElement(By.xpath("//select[@id='fcf']")), pViewName);
			
			driver.findElement(By.xpath("//select[@id='fcf']/following::span/a[text()='Edit']")).click();
			
			selectDropdown(driver.findElement(By.id("fcol1")),pField);
			selectDropdown(driver.findElement(By.id("fop1")),pOperator);
			driver.findElement(By.id("fval1")).clear();
			driver.findElement(By.id("fval1")).sendKeys(pValue);
			pause(1000);

			driver.findElement(By.xpath("(//*[@*='Save'])[1]")).click();
			LOGGER.info("View created. Name:" + pViewName );
			
			LOGGER.info("View selected. ");
			
		} catch (Exception e1) {
		   createViewWithListValuesAndOneFieldFilter(pViewName, pField, pOperator, pValue,   columnValues);
			LOGGER.info("View created");
		}

	}
	

	/**
	 * This method deletes the given view
	 * @param pViewName
	 */
	public void deleteView(String pViewName){

		try {
			
			LOGGER.info("Delete view");
			selectDropdown(driver.findElement(By.xpath("//select[@* = 'View:']")), pViewName);
					
			try {
				if (driver.findElement(By.xpath("//*[@title='Go!']")).isDisplayed()){
					driver.findElement(By.xpath("//*[@title='Go!']")).click();
					pause(3000);
				}
			} catch (Exception e) {
				LOGGER.info("Go! button is not displayed");
			}

			driver.findElement(By.xpath("//*[text()='Delete']")).click();
			pause(1000);
			
			Alert alert = driver.switchTo().alert();
			pause(2000);
			alert.accept();
			pause(2000);
			LOGGER.info("View has been deleted.");
			
		} catch (Exception e) {
			LOGGER.info("ERROR - Not able to delete the view. " + e.getMessage());
			Assert.fail("ERROR - Not able to delete the view. " + e.getMessage());
		}
	}


	/**
	 * Multiple select Picker - Validate list values
	 * 
	 * @param pListName - Name of the select Picker in GUI
	 * @param values - values need to be  choose or not choose
	 */
	public void validateMultilistValues(String pListName, List<String> values){
		try {
			LOGGER.info("To validate multi list values");
			Select dropDownAvialableBox = new Select(driver.findElement(By.xpath("//select[@id=concat(string(//label[contains(text(),'" + pListName +"')]/@for) , '_unselected' )]")));
			//Select dropDownChosenBox = new Select(driver.findElement(By.xpath("//select[@id=concat(string(//label[contains(text(),'"+ pListName +"')]/@for) , '_selected' )]")));
			
			List<String> actualValuetoAdd = new ArrayList<String>();

			//dropDownChosenBox.deselectAll();
			multipleSelectPickList(pListName, values, false);
			
			for(WebElement dropValue: dropDownAvialableBox.getOptions()){
				actualValuetoAdd.add(dropValue.getText());	
			}

			for(String value : values){
				if(actualValuetoAdd.contains(value)){
					LOGGER.info(value + " was found.");
				}else{
					LOGGER.info(value + " was not found.");
					Assert.fail(value + " was not found.");
				}
			}
		} catch (Exception e) {
			LOGGER.info("Value is in valid for list " + pListName);						
			Assert.fail("Value is in valid for list " + pListName);	
		}

	}

}

