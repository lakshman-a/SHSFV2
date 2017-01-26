package com.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage {

	public WebDriver driver;
	
	public GooglePage(WebDriver driver){

		this.driver = driver;
		if(driver.getTitle().equalsIgnoreCase("Google")){
			System.out.println("Google page is loaded");
			return;
		}
		
	}

	public GmailLoadPage goToGmailPage(){
		driver.findElement(By.linkText("Gmail")).click();
		return new GmailLoadPage(driver);
	}
	
	
	
}
