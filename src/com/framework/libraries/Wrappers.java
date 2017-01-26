package com.framework.libraries;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.collections.Lists;

import com.beust.jcommander.internal.Maps;
import com.framework.libraries.DataDrivenExcelSheet;

public class Wrappers {
	
	public ExcelReader envExcel;
	public ExcelReader suiteExcel;
	public Properties propValue = null;
	public FileInputStream fip;
	public String sheetName = null;
	public WebDriver driver;
	public List<String> arrLst = Lists.newArrayList();
	public List<String> lstTestcaseID = new ArrayList<String>();
	public List<String> lstAllComponents = new ArrayList<String>();
	
	DataDrivenExcelSheet data = new DataDrivenExcelSheet();
	
	//Get the environment details using HashMap
	public Map<String, HashMap<String, String>> getEnvironment(){
		
		//Hashmap parameters - >>Map with Hashmap
		Map<String,HashMap<String,String>> hmap = Maps.newHashMap();
		
		try{
			
		for(int i=1; i<envExcel.getNoOfRows(); i++){
		String statusCol = envExcel.getDataByColumn(i, "Status");
		
		if(statusCol.equalsIgnoreCase("Yes")){
			//Store the environment details in Hashmap
			HashMap<String,String> map = new HashMap<>();	
			String browserName = envExcel.getDataByColumn(i, "BrowerName");
			String browserURL = envExcel.getDataByColumn(i, "URL");
			String testSuiteName = envExcel.getDataByColumn(i, "TestSuite_Name");
//			enValues = enValues + browserName +" ## " +browserURL +" $$ "+testSuiteName + " ";

			map.put("BN", browserName);
			map.put("Url", browserURL);
			//Store Hashmap parameters in Hashmap
			hmap.put(testSuiteName, map);
			}
		
		}
		return hmap;
			}catch(Exception e){
			
				System.out.println("SheetName is not available in test environment");
		}
		return null;
	}
	
	public void browserDriver(){
		
		
	}
	
	public Map<String, List<String>> getSuite(){
		
		Map<String, List<String>> testcaseID = Maps.newLinkedHashMap();
		
		try{
			List<String> vals = Lists.newArrayList();
			
			for(int i = 1; i < suiteExcel.getNoOfRows(); i++){
				String testcaseName = suiteExcel.getDataByColumn(i, "Execute");
				
				if(testcaseName.equalsIgnoreCase("Yes")){
					
					String caseID = suiteExcel.getDataByColumn(i, "Test_Case_ID");
					vals = suiteExcel.getColumnNames(i);
					testcaseID.put(caseID, vals);
					
				}
			}
			
			
			/*for(String name : testcaseID.keySet()){
				System.out.println("Testcase ID :"+name);
				lstTestcaseID.add(name);
				
				
				for(String values : testcaseID.get(name)){
					System.out.println(values);
					lstAllComponents.add(values);
					
					
					
				}
			}*/
			
			for(String name : testcaseID.keySet()){
				System.out.println("Testcase ID :"+name);
				Iterator bb = testcaseID.get(name).iterator();
				
				//Initialize Environment Excel Sheet
				int a=0;
				DataDrivenExcelSheet dd = null;
				if(a==0){
					dd = new DataDrivenExcelSheet();
					dd.setSheet(testcaseID.get(name).get(a));
					
				a++;
				}
				while(bb.hasNext()){
					
				String parentName = bb.next().toString();
				
				System.out.println("parentName :"+parentName);
					
						data.dataDriven(parentName,dd);
				}
			}
			
			
		}catch(Exception e){
			System.out.println("SheetName is not available in test suite");
			e.printStackTrace();
		}
		return testcaseID;
	}
	
	
	
	
	public void initializeEnvExcel(){
		
		try{
		//Initialize Environment Excel Sheet
		envExcel = new ExcelReader("Configurations/Test_Setup/Test_Environment.xlsx", propValue.getProperty("environmentSheet"));
		
		}catch(Exception e){
			
			System.out.println("Environment sheet is not available in Configuration folder");
			
		}
	}
	
	public void initializeSuiteExcel(){
		
		try{
		//Initialize Environment Excel Sheet
		suiteExcel = new ExcelReader("Configurations/Test_Setup/Test_Suite.xlsx", propValue.getProperty("suiteName"));
		System.out.println("*** suiteExcel ***:" +suiteExcel.getSheetName());
		}catch(Exception e){
			
			System.out.println("Suite excel sheet is not available in Configuration folder");
		}
	}

	public void initializeFuncExcel(){
		try{
		//Initialize Functions Excel Sheet
//		functionsExcel = new ExcelReader("Configurations/Test_Cases/Functions.xlsx");
//		System.out.println("*** Functions SheetName :"+functionsExcel.getSheetNames());
		
		}catch(Exception e){
			
			System.out.println("Functions excel sheet is not available in Testcase folder");
			
		}
		
	}
	
	public String getFunctionVals(){
		
//		initializeSuiteExcel();
		getSuite();
		
		for(String name : getSuite().keySet()){
//			System.out.println("Testcase ID :"+name);
			lstTestcaseID.add(name);
			
			for(String values : getSuite().get(name)){
//				System.out.println(values);
				lstAllComponents.add(values);
				
				
			}
		}
		
		String funSheetValues = null;
		String allComponentValues = null;
		
		try{
			
//			List<String> funSheetNames = functionsExcel.getSheetNames();
//			arrLst.addAll(funSheetNames);
			for(int i = 0; i < arrLst.size(); i++){
				funSheetValues = arrLst.get(i);
				System.out.println( "funSheetValues :"+funSheetValues);
			}
			
			for(int j = 0; j < lstAllComponents.size(); j++){
				allComponentValues = lstAllComponents.get(j);
				System.out.println("allComponentValues :"+allComponentValues);
			}
			
		}catch(Exception e){
			
		}
		
		return null;
	}
	
	public void initializePropertyFiles(){
		
		try{
		//Initialize Property File
		propValue = new Properties();
		fip = new FileInputStream(System.getProperty("user.dir")+"/src/com/framework/properties/config.properties");
		propValue.load(fip);
		}catch(Exception e){
			
			System.out.println("Property file is not available");
		}
	}
	
	public static void main(String[] args) {
		
		try{
		
		Wrappers w = new Wrappers();
		//Initialize property files
		w.initializePropertyFiles();
		
		//Initialize and get Values from environment excel sheet
		/*w.initializeEnvExcel();
		System.out.println("Get Environment :"+w.getEnvironment());
		
		//Get the keySet from environment details from getEnvironment method using Hashmap
		for(String suiteName : w.getEnvironment().keySet()){
			System.out.println("My Env Suite Name :" + suiteName);
		for(String val : w.getEnvironment().get(suiteName).keySet()){
			System.out.println(w.getEnvironment().get(suiteName).get(val));
		}
		System.out.println("----------------------------------------------------");
		System.out.println("                                                     ");
		}*/
		
		//Initialize and get Values from Excel suite
		w.initializeSuiteExcel();
		w.getSuite();
		
		/*for(String name : w.getSuite().keySet()){
			System.out.println("Testcase ID :"+name);
			
			for(String values : w.getSuite().get(name)){
				System.out.println(values);
				
			}
		}*/
		
		//Initialize Functions excel sheet
		
		/*w.initializeFuncExcel();
		w.getFunctionVals();*/
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}