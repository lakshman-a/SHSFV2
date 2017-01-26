package com.framework.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.framework.pageObjects.GmailLoadPage;
import com.framework.pageObjects.GooglePage;

public class GmailPage {

	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "BrowserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.google.com");
		
		GooglePage googlePg = new GooglePage(driver);
		GmailLoadPage gmailLdPg = googlePg.goToGmailPage();
		System.out.println(gmailLdPg);
		driver.quit();
		
	}
	
	
}
