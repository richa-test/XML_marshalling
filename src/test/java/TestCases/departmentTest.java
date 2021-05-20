package TestCases;

import CommonClasses.BaseClass;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import payloadgeneration.DepartmentsPayload;
import utilities.ExcelUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class departmentTest  extends BaseClass {
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
    public void deptTest() throws Exception{
        ArrayList<HashMap<String,String>> deptDataList;
        String testDataFileName = "TestDataDepartment";
        String fileExtension = ".xlsx";
        String deptDataSheetName = "Department";
        String testDataFilePath = testDataRelativePath+testDataFileName+fileExtension;
        String xmlfilesPath = testResultsRelativePath + "xmlfiles";
        SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyyy_hhmmss");
        Date currDate = new Date();
        String strdateTime = sdf.format(currDate);
        String tmpFinalResultsFileName = testDataFileName.replace("TestData","FinalResults");
        String finalTestResultsFilePath = testResultsRelativePath + "finalResults\\"+tmpFinalResultsFileName +"_"+env+"_"+strdateTime+fileExtension ;
        DepartmentsPayload deptpayload = new DepartmentsPayload();

        deptDataList = ExcelUtil.getTestDataFromExcelFile(testDataFilePath,deptDataSheetName);

        String xmlPayloadFilePath = deptpayload.generateDepartmentPayload(deptDataList,xmlfilesPath);
        System.out.println("xmlPayloadFilePath-->\n"+xmlPayloadFilePath);

        String xmlFileName = deptpayload.getFileName();
        System.out.println("xmlFileName-->\n"+xmlFileName);

        System.out.println("payload--->\n"+deptpayload.getPayload());

        ArrayList<HashMap<String,String>> updatedDeptDataList = deptpayload.getDepartmentList();

        File sourceFile = new File(testDataFilePath);
        File destinationFile = new File(finalTestResultsFilePath);
        if(!destinationFile.exists()){
            FileUtils.copyFile(sourceFile, destinationFile);
        }

        for(int i = 0 ;i < updatedDeptDataList.size();i++){
            HashMap<String,String> deptDataUpdatedHashMap = updatedDeptDataList.get(i);
            String deptName = deptDataUpdatedHashMap.get("DepartmentName");
            ExcelUtil.writeResultForSpecifiedColumn(finalTestResultsFilePath,deptDataSheetName,"DepartmentName",deptName,deptDataUpdatedHashMap);
        }

    }



}
