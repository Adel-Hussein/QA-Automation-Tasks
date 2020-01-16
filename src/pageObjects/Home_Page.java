package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utility.Log;

import java.util.List;

public class Home_Page {
	
	private static WebElement element = null;
	
	private static List<WebElement> elementList = null;
	
	public static WebElement search_Bar(WebDriver driver){
		 
	    element = driver.findElement(By.id("headerBox"));
	 
	    Log.info("Search Bar element found");

	    return element;
 	    }
	
	public static WebElement flyout(WebDriver driver){
		
		try {
			element = driver.findElement(By.className("unbxd-as-wrapper"));
			
			System.out.println("flyout element found");
			
			Log.info("flyout element found");

		    return element;
		}catch(Exception e){
			
			System.out.println("flyout element Not found");
			
			Log.warn("flyout element Not found");
			
			return null;
		}
	    
	 
	    
 	    }
	
 	
	public static List<WebElement> suggested_Search_Terms(WebDriver driver){
		 
		try {
		
		elementList = driver.findElements(By.className("unbxd-as-keysuggestion"));
	 
	    Log.info("suggested Search Terms Elements found");

	    return elementList;
	    
		}catch(Exception e) {
			System.out.println("sababaaaaa");
			return null;
		}
	 
	    }
	
	public static List<WebElement> suggested_Products(WebDriver driver){
		 
		elementList = driver.findElements(By.className("unbxd-as-popular-product"));
	 
	    Log.info("suggested Search Terms Elements found");

	    return elementList;
	 
	    }
	
	
	

}
