package com.framework.pageObjects;

import org.openqa.selenium.WebDriver;

public class GmailLoadPage {
	
	public WebDriver driver;
	
	public GmailLoadPage(WebDriver driver) {
		this.driver = driver;
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().contains("Gmail")){
			System.out.println("Gmail Loaded");
		}else{
			System.out.println("Gmail not Loaded");
		}

	}
	

	

}
