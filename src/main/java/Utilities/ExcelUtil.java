package Utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

  //  private static final Logger logger = LogManager.getLogger(ExcelUtil.class);

    /**
     * This method is used to read data from excel file as a arraylist of hashmap having column name and its data
     * @param testDataPath
     * @param sheetName
     * @return testDataArrayListHashMap
     * @throws IOException
     */


    public static ArrayList<HashMap<String,String>> getTestDataFromExcelFile(String testDataPath,String sheetName) throws IOException{
        ArrayList<HashMap<String,String>> testDataArrayListHashMap = new ArrayList<HashMap<String,String>>();
        FileInputStream fileInputStream = new FileInputStream(testDataPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        try{
            Sheet sheet = workbook.getSheet(sheetName);
            int totalRows = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);
            int totalColumns = headerRow.getPhysicalNumberOfCells();
            String dataCellValue;

//looping through rows
            for(int i = 1;i < totalRows;i++){
                Row currentRow = sheet.getRow(i);
                HashMap<String,String> currentRowHashMap = new HashMap<String,String>();
//looping through columns
                for(int j =0;j < totalColumns;j++){
//to get data for columns in current row
                    Cell currentCell = currentRow.getCell(j);
                    if(currentCell.getCellType() == CellType.STRING){
                        dataCellValue = currentCell.getStringCellValue();
                        currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
                    }else if(currentCell.getCellType() == CellType.NUMERIC){
                        dataCellValue = String.valueOf(currentCell.getNumericCellValue());
                        currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
                    }else if(currentCell.getCellType() == CellType.BOOLEAN){
                        dataCellValue = String.valueOf(currentCell.getBooleanCellValue());
                        currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
                    }else if(currentCell.getCellType() == CellType.BLANK){
                        dataCellValue = "";
                        currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
                    }
                }//closing of column loop
                testDataArrayListHashMap.add(currentRowHashMap);
            }//close of row loop
        }catch(Exception exception){
          //  logger.error(exception.getMessage(), exception);
            exception.printStackTrace();
        }finally{
            fileInputStream.close();
            workbook.close();
        }
        return testDataArrayListHashMap;
    }
}
