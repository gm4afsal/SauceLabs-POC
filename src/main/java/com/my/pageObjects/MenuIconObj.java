package com.my.pageObjects;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/* AUTHOR: ABacker
 * 
 * 21/08/2019
 */


public class MenuIconObj extends Page{
	public static final Logger LOGGER = LogManager.getLogger(MenuIconObj.class);
	
	@Rule
	public ErrorCollector errCol = new ErrorCollector();

	WebDriver driver;
	public MenuIconObj (WebDriver driver){
	       super (driver);
   }
	
	@FindBy(xpath="//*[@id='tsidLabel']")
	private WebElement userProfileMenu;
	
	@FindBy(xpath="//*[@id='tsid-arrow']")
	private WebElement ProfilesArrow;
	
	@FindBy(xpath="//*[@id='tsid-menuItems']/a[1]")
	private WebElement sales;
	
	@FindBy(xpath="//*[@id='tsid-menuItems']/a[2]")
	private WebElement callCenter;
	
	@FindBy(xpath="/*[@id='tsid-menuItems']/a[3]")
	private WebElement marketing;
	
	@FindBy(xpath = "//*[@id='tsid-menuItems']/a[4]")
	private WebElement appLauncher;
	
	@FindBy(xpath = "//*[@id='tsid-menuItems']/a[7]")
	private WebElement devCommunity;
	
	@FindBy(xpath = "//*[@id='tsid-menuItems']/a[8]")
	private WebElement successCom;
	
	@FindBy(xpath="//*[@id='phHeader']/tbody/tr/td[3]/div/div[2]/div/a")
	private WebElement helpTrain;
	
	@FindBy(xpath = "//*[@id='userNavLabel']")
	private WebElement myProfileMenu;
	
	@FindBy(xpath = "//*[@id='userNav-arrow']")
	private WebElement myProfileArrow;
	
	@FindBy(id = "//*[@id='userNav-menuItems']/a[2]")
	private WebElement myprofileLink;
	
	@FindBy(id = "//*[@id='userNav-menuItems']/a[3]")
	private WebElement setup;
	
	@FindBy(id = "//*[@id='userNav-menuItems']/a[4]")
	private WebElement devConsole;
	
	//@FindBy(xpath = "//*[@id='userNav-menuItems']/a[4]")
	@FindBy(linkText = "Logout")
	private WebElement logOut;
	
	@FindBy(xpath = "//*[@id='rememberUn']")
	private WebElement remChkBox;
	
	@FindBy(id = "/*[@id='phHeaderLogoImage']")
	private WebElement salesLogoImage;
	
	@FindBy(id = "//*[@id='bodyCell']/div[1]/div[1]/div[2]/a/span")
	private WebElement helpLink;
	
	@FindBy(linkText = "Service Cloud Console'")
	private WebElement serviceCloudConsole;
	
	
	public void logout()  { 
		  myProfileMenu.click();
		  pause (3000);
		  logOut.click();	 
	}
	
	public void selectMenuIcon() {
		// TODO Auto-generated method stub
		 userProfileMenu.click();   
	}

	public void gotoConsoleWindow(){
		userProfileMenu.click();
		pause(1000);
		serviceCloudConsole.click();
		pause(1000);
	}


}