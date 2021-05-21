package utilities;
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


    /**
     * This method is used to write result data -resultHashMap to excel file for respective testCaseName
     * @param testDataPath
     * @param sheetName
     * @param testCaseName
     * @param resultHashMap
     * @throws IOException
     */

    public static void writeResultForTestCase(String testDataPath,String sheetName,String testCaseName,HashMap<String,String> resultHashMap) throws IOException{
        HashMap<String,String>  testDataHashMapForTestCase = new HashMap<String,String>();
        HashMap<String,Integer>  testDataColumnsHashMap = new HashMap<String,Integer>();
        FileInputStream fileInputStream = new FileInputStream(new File(testDataPath));
        XSSFWorkbook workbook = new XSSFWorkbook( fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
/** get column index in excel file for the column names **/
        XSSFRow row = sheet.getRow(0);//get first row
        short minColIndx = row.getFirstCellNum();
        short maxColIndx = row.getLastCellNum();
        for(short colIndx =minColIndx ; colIndx < maxColIndx; colIndx ++){
            XSSFCell cell = row.getCell(colIndx);//header cell
            testDataColumnsHashMap.put(cell.getStringCellValue(),cell.getColumnIndex());
        }
/** below code is to write the data to excel file as per testcase name**/
        int testCaseColumnIndex = testDataColumnsHashMap.get("TestCaseName");
        int testCaseRowIndexToWrite = 1;
        int totalRows = sheet.getPhysicalNumberOfRows();
        int columnIndexToWrite;
        HashMap<String,String> tempData;
//get row number of the testcase
        for(int i =1;i< totalRows;i++){
            if(sheet.getRow(i).getCell(testCaseColumnIndex).getStringCellValue().equalsIgnoreCase(testCaseName)){
                testCaseRowIndexToWrite = i;
                break;
            }
        }
//write values for the test case
        for(Map.Entry<String,String> entry:resultHashMap.entrySet()){
            String key = entry.getKey();
        String value = entry.getValue();
        if(value.equals("")||key.equalsIgnoreCase("TestCaseName")){
            continue;
        }
//get column index to writeData
        columnIndexToWrite = testDataColumnsHashMap.get(key);//gets index for the column name
        XSSFCell writeToCell = sheet.getRow(testCaseRowIndexToWrite).createCell(columnIndexToWrite);
        writeToCell.setCellValue(value);
    }
    FileOutputStream fos = new FileOutputStream(testDataPath);
    workbook.write(fos);
    fos.close();
    fileInputStream.close();
    workbook.close();
}


/**
 * This method is used to write result data -resultHashMap to excel file for respective specifiedColumn with its specific data
 * Similar to testcase column as our reference as uniqueness ,here we specify the column
 * @param testDataPath
 * @param sheetName
 * @param testCaseName
 * @param resultHashMap
 * @throws IOException
 */
    /**Make sure the row data for specified column is unique like the testcaseName/number**/
     public static void writeResultForSpecifiedColumn(String testDataPath,String sheetName,String columnName,String rowDataForColumn,HashMap<String,String>resultHashMap) throws IOException{
     Map<String,Integer> testDataColumnsHashmap = new HashMap<String,Integer>();
     FileInputStream fileInputStream = new FileInputStream(new File(testDataPath));
     XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
     XSSFSheet sheet = workbook.getSheet(sheetName);
     /** below code is to get the column index in excel file for the column names into a hashmap**/
    XSSFRow row = sheet.getRow(0);//get first row
    short minColIndx = row.getFirstCellNum();
    short maxColIndx = row.getLastCellNum();
    for(short colIndx = minColIndx;colIndx < maxColIndx;colIndx++) {
        XSSFCell cell = row.getCell(colIndx);//header cell
        testDataColumnsHashmap.put(cell.getStringCellValue(),cell.getColumnIndex());
    }
    /** below code is to write the data to excel file as per column name**/
    int testCaseColumnIndex = testDataColumnsHashmap.get(columnName);
    int testCaseRowIndexToWrite =1;
    int totalRows = sheet.getPhysicalNumberOfRows();
    int columnIndexToWrite;

//get row number of specified columnName with its unique value
	for(int i =1;i<totalRows;i++) {
        if (sheet.getRow(i).getCell(testCaseColumnIndex).getStringCellValue().equalsIgnoreCase(rowDataForColumn)) {
            testCaseRowIndexToWrite = i;
            break;
        }
    }
//write values for the  row for which we have the resultmap
        for (Map.Entry<String,String> entry : resultHashMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.equals("")||key.equalsIgnoreCase(columnName)){
                continue;
            }
//get column index to write data
            columnIndexToWrite = testDataColumnsHashmap.get(key);
            XSSFCell writeToCell = sheet.getRow(testCaseRowIndexToWrite).createCell(columnIndexToWrite);
            writeToCell.setCellValue(value);
        }
        FileOutputStream fos = new FileOutputStream(testDataPath);
        workbook.write(fos);
        fos.close();
        fileInputStream.close();
        workbook.close();

}


    /**
     Description: the method is used to get TestCaseName and a HashMap of data for the respective TestCase
     *returns hashmap(testcasename,testdata of the testcase as hashmap)-hashmap of testcase name and testdata for the testcase as ahashmap

     */

    public static HashMap<String,HashMap<String,String>> getTestDataForTestCase(String testDataPath,String sheetName) throws IOException{
        HashMap<String,HashMap<String,String>> testDataHashMapForTestCase = new   HashMap<String,HashMap<String,String>>();
        String testCaseName = "initialTc";
        Map<String,Integer> testDataColumnsHashMap = new HashMap<String,Integer>();

        FileInputStream file = new FileInputStream(testDataPath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getPhysicalNumberOfRows();
        Row headerRow = sheet.getRow(0);
        int totalColumns = headerRow.getPhysicalNumberOfCells();
        String dataCellValue ;

//looping through rows
        for(int i =1;i< totalRows;i++){
            Row currentRow = sheet.getRow(i);
            HashMap<String,String> currentRowHashMap = new HashMap<String,String>();
//to get testCase Name
            Cell testCaseNameCell = currentRow.getCell(0);//first column has to be TestCaseName
            if(testCaseNameCell.getCellType() == CellType.STRING){
                testCaseName = testCaseNameCell.getStringCellValue();
            }
        else if(testCaseNameCell.getCellType() == CellType.NUMERIC){
            testCaseName = String.valueOf(testCaseNameCell.getNumericCellValue());
        }
    //looping through columns
        for(int j=0;j<totalColumns;j++){
    //to get data for other columns in current row
            Cell currentCell = currentRow.getCell(j);
            if(currentCell.getCellType() ==  CellType.STRING){
                dataCellValue = currentCell.getStringCellValue();
                currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
            }else if(currentCell.getCellType() == CellType.NUMERIC){
                dataCellValue = String.valueOf(currentCell.getNumericCellValue());
                currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
            }else if(currentCell.getCellType() == CellType.BOOLEAN){
                dataCellValue = String.valueOf(currentCell.getBooleanCellValue());
                currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(),dataCellValue);
            }else if(currentCell.getCellType() == CellType.BLANK) {
                dataCellValue = "";
                currentRowHashMap.put(headerRow.getCell(j).getStringCellValue(), dataCellValue);
            }
          }//closing of column loop
            testDataHashMapForTestCase.put(testCaseName,currentRowHashMap);
        }//close of row loop
        workbook.close();
        file.close();
        return testDataHashMapForTestCase;
    }

    /**
     Description: the method is used to get append rows existing excel file**/


    public static void writeToExcelAppending(String filePath,String sheetName,HashMap<String,String> dataHashMap) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Map<String,Integer> testDataColumnsHashMap = new HashMap<String,Integer>();
/** below code is to get the column index in excel file for the column names in hashmap**/
        XSSFRow row = sheet.getRow(0);
        int totalRows = sheet.getLastRowNum();
        int rowCount = ++totalRows;

        short minColIndx = row.getFirstCellNum();
        short maxColIndx = row.getLastCellNum();
        for (short colIndx = minColIndx;colIndx < maxColIndx; colIndx ++){
            XSSFCell cell = row.getCell(colIndx );//headercell
            testDataColumnsHashMap.put(cell.getStringCellValue(),cell.getColumnIndex());
        }
/** below code is to write the data to excel file as per column as in hasmap and excel file **/
        int columnIndexToWrite;
        sheet.createRow(rowCount);
        for(Map.Entry<String,String> entry:dataHashMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
//get column index to write
            columnIndexToWrite = testDataColumnsHashMap.get(key);
            XSSFCell writeToCell = sheet.getRow(rowCount).createCell(columnIndexToWrite);
            writeToCell.setCellValue(value);
        }
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        fileInputStream.close();
    }





}
