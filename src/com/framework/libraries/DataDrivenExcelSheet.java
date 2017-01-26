package com.framework.libraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataDrivenExcelSheet {
	
	public ExcelReader functionExcel;
	public WebDriver driver = null;
	
	
	
	public void setSheet(String sheetName) throws FileNotFoundException, IOException{
		this.functionExcel = new ExcelReader("Configurations/Test_Cases/Functions.xlsx", sheetName);

	}
	
	
	public void getFunctionSheetNames(){
		
		
		List<String> sheetNames = functionExcel.getSheetNames();
		
		ListIterator<String> itr = sheetNames.listIterator();
		
		while(itr.hasNext()){
			
			String allValues = itr.next();
			
			System.out.println(allValues);
			
			if(allValues.equalsIgnoreCase("Gmail")){
				
				System.out.println("Passed");
				
			}
			
		}
		
		
	}
	
	
	public HashMap<String, String> dataDriven(String parentSheetName,DataDrivenExcelSheet dd){
		
		HashMap<String,String> map = new HashMap<>();
		this.functionExcel = dd.functionExcel;
		try{
			
			
			//Initialize Environment Excel Sheet
			
			
					System.out.println("Sheet Name Matched ");
					
					for(int i=1; i<functionExcel.getNoOfRows(); i++){
						
						//Store the environment details in Hashmap
						String keywords = functionExcel.getDataByColumn(i, "Keywords");
						
						
						if(parentSheetName.equalsIgnoreCase(keywords)){
						String testDesc = functionExcel.getDataByColumn(i, "Test_Description");
						String objects = functionExcel.getDataByColumn(i, "Objects_Reps");
						String events = functionExcel.getDataByColumn(i, "Events");
						String testData = functionExcel.getDataByColumn(i, "Test_Data");
						
//						executeEvent(objects, events, testData);
						
						
						map.put("keywords", keywords);
						map.put("testDesc", testDesc);
						map.put("objects", objects);
						map.put("events", events);
						map.put("testData", testData);
						
						System.out.println(map);
						}
					}
					
//			}
			
			return map;
			
			
		}catch(Exception e){
			
			
		}
		return null;
		
		
	}
	
	
	
	
	public String executeEvent(String locator,String eventName,String testdata){
		
		
		String locatorType = null;
		String locatorName = null;
		
		WebElement element = driver.findElement(By.id(locator));
		
		switch(eventName.toLowerCase()){
		
		case "Enter":
			element.sendKeys(testdata);
			return "Typed the value";
		}
		return null;
		
		
		
	}
	
	
	public By byLocatorType(String locatorType, String locator){
		
		By by = null;
		
		try{
			
			
			
			
		}catch(Exception e){
			
			
			
			
		}
		return by;
		
		
		
	}
	
	
	
	
	
	public void initializeSuiteExcel(){
		
		try{
		//Initialize Environment Excel Sheet
//		functionExcel = new ExcelReader("Configurations/Test_Cases/Functions.xlsx", "GitHub");
		
		}catch(Exception e){
			
			System.out.println("Suite excel sheet is not available in Configuration folder");
		}
	}

	
	public static void main(String[] args) {
		
		DataDrivenExcelSheet data = new DataDrivenExcelSheet();
//		data.initializeSuiteExcel();
//		data.getFunctionSheetNames();
//		data.dataDriven("GitHub");
//		System.out.println(data.dataDriven());
		
	}
	
}
