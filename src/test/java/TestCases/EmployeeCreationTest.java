package TestCases;

import CommonClasses.BaseClass;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileUtil;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import payloadgeneration.EmployeeCreationPayload;
import utilities.ExcelUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EmployeeCreationTest extends BaseClass {

    @BeforeTest
    public static void configread(){
        try{
            BaseClass bc = new BaseClass();
            bc.executionProperties();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void employeesCreationTest() throws Exception{
        ArrayList<HashMap<String,String>> employeeDataList;
        String testDataFileName = "TestDataEmployeeCreation";
        String fileExtension = ".xlsx";
        String employeeDataSheetName = "Employees";
        String testDataFilePath = testDataRelativePath+testDataFileName+fileExtension;
        String xmlfilesPath = testResultsRelativePath + "xmlfiles";
        SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyy_hhmmss");
        Date currDate = new Date();
        String strdateTime = sdf.format(currDate);
        String tmpFinalResultsFileName = testDataFileName.replace("TestData","FinalResults");
        String finalTestResultsFilePath = testResultsRelativePath + "finalResults\\"+tmpFinalResultsFileName +"_"+env+"_"+strdateTime+fileExtension ;
        EmployeeCreationPayload employeepayload = new EmployeeCreationPayload();

        employeeDataList = ExcelUtil.getTestDataFromExcelFile(testDataFilePath,employeeDataSheetName);

        String xmlPayloadFilePath = employeepayload.generateEmployeeCreationPayload(employeeDataList,xmlfilesPath);
        System.out.println("xmlPayloadFilePath-->\n"+xmlPayloadFilePath);

        String xmlFileName = employeepayload.getFileName();
        System.out.println("xmlFileName-->\n"+xmlFileName);

        System.out.println("payload--->\n"+employeepayload.getPayload());

        ArrayList<HashMap<String,String>> updatedEmployeeDataList = employeepayload.getEmployeeCreationList();

        File sourceFile = new File(testDataFilePath);
        File destinationFile = new File(finalTestResultsFilePath);
        if(!destinationFile.exists()){
            FileUtils.copyFile(sourceFile, destinationFile);
        }

        for(int i = 0 ;i < updatedEmployeeDataList.size();i++){
            HashMap<String,String> employeeDataUpdatedHashMap = updatedEmployeeDataList.get(i);
            String testCaseName = employeeDataUpdatedHashMap.get("TestCaseName");
            ExcelUtil.writeResultForTestCase(finalTestResultsFilePath,employeeDataSheetName,testCaseName,employeeDataUpdatedHashMap);
        }

    }


}
