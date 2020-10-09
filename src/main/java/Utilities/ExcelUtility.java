package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class ExcelUtility {

	public String TestData_Filepath;
	public String testcasesheet;
	public FileInputStream fileInputStream;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFSheet refSheet;
	public XSSFRow rowObj;
	int rowcount;
	public HashMap<String, String> TestDataMap;

	public void openExcelfile(String TestData_Filepath, String Sheet_name) throws IOException {
		this.TestData_Filepath = TestData_Filepath;
		fileInputStream = new FileInputStream(new File (TestData_Filepath));
		wb = new XSSFWorkbook(fileInputStream);
		sheet = wb.getSheet(Sheet_name);
		refSheet = wb.getSheet("ViewMandates");
		rowcount = sheet.getLastRowNum();
		System.out.println("Sheet " + Sheet_name + "has total Rowcount " + (rowcount + 1));
	}

	public void closeExcelfile() throws IOException {
		wb.close();
		fileInputStream.close();
	}
	
	public int getRowCount() {
		int totalNumberOfRows = sheet.getLastRowNum();
		return totalNumberOfRows;
	}

	public String getCellStringValue(Cell cell) {
		if (null != cell) {
			switch (cell.getCellType()) {
			case BLANK:
				return "";
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case ERROR:
				return "";
			case FORMULA:
				return String.valueOf(cell.getCellFormula());
			case NUMERIC:
				final DataFormatter formatter = new DataFormatter();
	            return formatter.formatCellValue(cell);			/*	Double d = cell.getNumericCellValue();
				Long l = d.longValue();
				if (l.doubleValue() == d) {
					return l.toString();
				} else {
					return d.toString();
				}*/	
			default:
				return cell.getStringCellValue();
			}
		}
		return "";
	}

	public HashMap<String, String> getEmployeeTestData(int rowNumber) throws IOException {
		TestDataMap = new HashMap<String, String>(); // Create map
				rowObj = sheet.getRow(rowNumber);
				int minColIx = sheet.getRow(0).getFirstCellNum();
				int maxColIx = sheet.getRow(0).getLastCellNum();
		
				for (int colIx = minColIx; colIx < maxColIx; colIx++) {
					XSSFCell cell = rowObj.getCell(colIx);
					
					TestDataMap.put(sheet.getRow(0).getCell(colIx).toString(),getCellStringValue(cell));
					
				}
				return TestDataMap;

			}
}
