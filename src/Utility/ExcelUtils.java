package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static XSSFSheet ExcelWSheet;
	 
    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow xRow;
    
    public static void setExcelFile(String Path,String SheetName) throws Exception {
   	 
        try { 
   
  
			  FileInputStream ExcelFile = new FileInputStream(Path);
			  			  
			  ExcelWBook = new XSSFWorkbook(ExcelFile);
			  
			  ExcelWSheet = ExcelWBook.getSheet(SheetName);
			  
			  } catch (Exception e){
  
				  throw (e);
				  
				  }
  
    }
    
    public static String getCellData(int RowNum, int ColNum) throws Exception{
		 
	       try{
	 
	           Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	 
	           String CellData = Cell.getStringCellValue();
	 
	           return CellData;
	 
	           }catch (Exception e){
	  
	        	  
	        	  System.out.println("ADEL  "+e.getMessage());
	        	  
	        	  return"";
	           }
	 
	     }
	
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception {
		 
	       try{ 
	 
	    	   xRow  = ExcelWSheet.getRow(RowNum);
	 
				Cell = xRow.getCell(ColNum);
				 
				if (Cell == null) {
				 
				Cell = xRow.createCell(ColNum);
				 
				Cell.setCellValue(Result);
				 
				} else {
				 
					Cell.setCellValue(Result);
				 
				}
	 
	          // Constant variables Test Data path and Test Data file name
	 
	           FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
	 
	           ExcelWBook.write(fileOut);
	 
	           fileOut.flush();
	 
	 fileOut.close();
	 
	 }catch(Exception e){
	 
	 throw (e);
	 
	 }
	 
	 }

	public static String getTestCaseName(String sTestCase)throws Exception{

		String value = sTestCase;

		try{

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");	

			value = value.substring(posi + 1);

			return value;

				}catch (Exception e){

			throw (e);

					}

		}
	
	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
		 int i;
		    try {
		     int rowCount = ExcelWSheet.getLastRowNum();
		        for ( i=0 ; i<rowCount; i++){
		         if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
		             break;
		            }
		         }
		        return i;
		    }catch (Exception e){
		     Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
		        throw(e);
		     }
		    }
	

}
