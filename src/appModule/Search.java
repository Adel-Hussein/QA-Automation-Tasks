package appModule;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import Utility.Constant;
import Utility.ExcelUtils;
import Utility.Log;
import pageObjects.Home_Page;

public class Search {
	
	public static String Search_Key;
	
	public static List<String> suggested_Search_Terms_List ;
	
	public static List<String> suggested_Products_List ;
	
	public static boolean isFlyOutDisplay ;

	
	public static boolean Execute(WebDriver driver, int iTestCaseRow) throws Exception{
		
		Search_Key = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_SearchKey);
		
		Log.info("Search Key picked from Excel is "+ Search_Key);
		
		Home_Page.search_Bar(driver).sendKeys(Search_Key);
		
		Log.info("Search Key entered in the Search Bar");
		Thread.sleep(5000);
		
//		WebElement element = driver.findElement(By.className("unbxd-as-wrapper"));
//		Actions builder = new Actions(driver); 
//		Actions hover = builder.moveToElement(element);
//		hover.build().perform();
		
		try {
			
			WebElement element = driver.findElement(By.className("unbxd-as-wrapper"));
			Actions builder = new Actions(driver); 
			Actions hover = builder.moveToElement(element);
			hover.build().perform();
			
			if(Home_Page.flyout(driver).isDisplayed()) { //maybe there id EXC here
			
			suggested_Search_Terms_List = getSuggestedSearchTerms(driver);
			
			Log.info("get Suggested Search Terms ..");
			
			suggested_Products_List = getSuggestedProducts(driver);
			
			Log.info("get Suggested Products ..");
			
			return true;
			
			}else {
				
				Log.info("There is No Suggested Search Terms ..");
				
				Log.info("There is No Suggested Products ..");
				
				return false;
	
			}
		
		}catch(Exception e) {
			
			System.out.println("flyout element Not found");
			
			Log.warn("flyout element Not found");
			
			return false;
		}
		
		
		
		
	}


	private static List<String> getSuggestedSearchTerms(WebDriver driver) {
		
		List<String> list = new ArrayList<String>();
		
		List<WebElement> elementList = Home_Page.suggested_Search_Terms(driver);
		
		for(WebElement element : elementList ) {
			
			list.add(element.getAttribute("data-value"));
			
			
		}
		
		return list;
	}
	
	private static List<String> getSuggestedProducts(WebDriver driver) {
		
	
		List<String> list = new ArrayList<String>();

		List<WebElement> elementList = Home_Page.suggested_Products(driver);
		
		for(WebElement element : elementList ) {
			
			list.add(element.getAttribute("data-value"));
			
		}
		
		
		return list;
	}
	
	public static boolean verifySuggestedSearchTerms() {
		
		int i =0;
		
		while(i < suggested_Search_Terms_List.size()) {			
		
			if(! suggested_Search_Terms_List.get(i).toLowerCase().contains(Search_Key.toLowerCase())) {

				return false;
			}
			i++;
		}
		
		return true;
	}
	
	public static boolean verifySuggestedProducts() {
		int i =0;
		
		while(i < suggested_Products_List.size()) {
			
			if(! suggested_Products_List.get(i).toLowerCase().contains(Search_Key.toLowerCase())) {

				return false;
			}
			i++;
		}
		
		return true;
	}
	
	public static void CheckResult(int iTestCaseRow, boolean isVerifySuggestedTerms, boolean isVerifySuggestedProducts) throws Exception {
		
		if(isVerifySuggestedTerms) {
			
			 System.out.println("Suggested Search Terms List : Pass");
			  
			  Log.info("Suggested Search Terms List : Pass");
		}else {
			
			System.out.println("Suggested Search Terms List : Fail");
			  
			 Log.info("Suggested Search Terms List : Fail");
			
		}
		
		if(isVerifySuggestedProducts) {
			
			 System.out.println("Suggested Products List : Pass");
			  
			  Log.info("Suggested Products List : Pass");
		}else {
			
			System.out.println("Suggested Products List : Fail");
			  
			 Log.info("Suggested Products List : Fail");
			
		}
		
		
		if(isVerifySuggestedTerms && isVerifySuggestedProducts) {
			  
			  System.out.println("the total search process result is  pass");
			  
			  Log.info("the total search process result is  passd");
			  
			  ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
		  }else {
			  System.out.println("the total search process result is failed");
			  
			  Log.info("the total search process result is failed");
			  
			  ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
		  }
		
	}
	
	

}
