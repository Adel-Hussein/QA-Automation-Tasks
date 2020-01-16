package automationFramework;

import org.testng.annotations.Test;

import Utility.Browser;
import Utility.Constant;
import Utility.ExcelUtils;
import Utility.Log;
import appModule.Search;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class SearchChair {
	
	public WebDriver driver;
	
	private String sTestCaseName;
	 
    private int iTestCaseRow;
    
    private boolean isVerifySuggestedTerms;
    
    private boolean isVerifySuggestedProducts;
    

	  @BeforeClass
	  public void beforeMethod() throws Exception {
		  
		System.setProperty("webdriver.gecko.driver","//Users//adelhussein1//Downloads//geckodriver");

		DOMConfigurator.configure("log4j.xml");
		
		sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
		
		Log.startTestCase(sTestCaseName);
		   
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		
		Log.info(" Excel sheet opened");
		
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
		
		driver = Browser.openBrowser();
	  }
    
	  @Test
	  public void f() throws Exception {
		  
		 boolean isSearchDone = Search.Execute(driver, iTestCaseRow);
		  
		 if(isSearchDone) {
			 
			 System.out.println("the search process executed ..");
			  
			  isVerifySuggestedTerms = Search.verifySuggestedSearchTerms();
			  
			  isVerifySuggestedProducts = Search.verifySuggestedProducts();
			  
			  System.out.println("Check the Results of Search ..");
			  
			  Search.CheckResult(iTestCaseRow, isVerifySuggestedTerms, isVerifySuggestedProducts);
			  
		 }else {
			 System.out.println("the search process failed ..");
			 Log.info("We could not find results for"+sTestCaseName);
		 }
		  
	  }
	
	  @AfterClass
	  public void afterMethod() {
		  
		  Browser.closeBrowser(driver);
		  
		  Log.endTestCase(sTestCaseName);
		  
	  }

}
