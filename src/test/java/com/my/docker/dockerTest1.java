package com.my.docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class dockerTest1
{
	@Test
	public void createDocker1() throws MalformedURLException
	{
		System.out.print("Starting Test 2");
		System.out.println();
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		
		//DesiredCapabilities dc = DesiredCapabilities.firefox();
		
		URL dockerUrl = new URL("http://localhost:4444/wd/hub");
		
		RemoteWebDriver driver = new RemoteWebDriver(dockerUrl,dc);
		
		driver.get("https://google.com");
		
		driver.getTitle();
		
		System.out.print("Title is: " +driver.getTitle());
		System.out.println();
		
		driver.quit();
	}
}
 