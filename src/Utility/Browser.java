package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
	
	public static WebDriver driver = null;
	
	public static WebDriver openBrowser() {
		
		try {
			
		driver = new FirefoxDriver();
		
		Log.info("New driver instantiated");
		 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Log.info("Implicit wait applied on the driver for 10 seconds");

        driver.get(Constant.URL);

        Log.info("Web application launched successfully");
        
        driver.manage().window().maximize();
        
		}catch(Exception e){
 
            Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
 
        }
 
        return driver;
		
		
	}
	
	public static void closeBrowser(WebDriver driver) {
		
		driver.quit();
        
        Log.info("Browser closed");
        
        
	}
	

}
