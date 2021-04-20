package genericlibs;
import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

// Class to return the test data stored in the Excel Sheet
public class ExcelLibrary implements AutoConstants {
	
	public static Workbook workbook;
	
	//To Create Only one Workbook Type Object in the entire framework execution
	static {
		try {
			FileInputStream file = new FileInputStream(XL_PATH);
			workbook=WorkbookFactory.create(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getStringData(String sheetName, int rowNumber, int cellNumber) {
		return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
	}
	
	public static double getNumericData(String sheetName, int rowNumber, int cellNumber) {
		return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getNumericCellValue();
	}
	
	public static boolean getBooleanData(String sheetName, int rowNumber, int cellNumber) {
		return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getBooleanCellValue();
	}
	
	public static LocalDateTime getLocalDateAndTimeData(String sheetName, int rowNumber, int cellNumber) {
		return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getLocalDateTimeCellValue();
	}
	
	public static String[][] getMultipleData(String sheetName) {
		Sheet sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getPhysicalNumberOfRows();
		int cellCount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		String[][] sarr = new String[rowCount-1][cellCount];
		
		for(int i=1,k=0;i<=rowCount-1;i++, k++) {
			for(int j=0;j<=cellCount-1;j++) {
				sarr[k][j] = sheet.getRow(i).getCell(j).toString();
			}
		}
		
		return sarr;
	}
}
