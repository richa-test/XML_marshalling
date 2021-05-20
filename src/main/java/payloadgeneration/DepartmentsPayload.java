package payloadgeneration;

import pojos.EmployeePojos.Employee;
import pojos.EmployeePojos.Employees;
import pojos.departmentpojos.Department;
import pojos.departmentpojos.Departments;
import pojos.departmentpojos.Document;
import pojos.departmentpojos.Recovery;
import utilities.DataGenerator;
import utilities.DateFunctions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentsPayload {
    ArrayList<HashMap<String,String>> departmentList;

    String fileName;
    String payload;


    public String generateDepartmentPayload(ArrayList<HashMap<String,String>> departmentDataList,String fileLocation) throws Exception {

        departmentList = new ArrayList<HashMap<String, String>>();
        fileName = "DEP" + DateFunctions.getFormattedCurrentDate("yyyyMMddHHmmSSS") + DataGenerator.generateRandomLong(6);
        String filePath = fileLocation + "\\" + fileName;
        Document document = new Document();
        Departments departments = new Departments();
        departments.setDepartment(new ArrayList<Department>());

        HashMap<String, String> departmentHashMap = new HashMap<String, String>();
        for (int i = 0; i < departmentDataList.size(); i++) {
            departmentHashMap = departmentDataList.get(i);
            Department dept = new Department();

            //set employee values
            String depNo = DataGenerator.generateDepartmentId(i);
            departmentHashMap.put("DepartmentNumber", depNo);
            dept.setDepartmentNo(depNo);
            dept.setDepartmenName(departmentHashMap.get("DepartmentName"));
            Recovery recovery = new Recovery();

            String curr = departmentHashMap.get("Currency");
            String recoveryAmt = departmentHashMap.get("RecoveryMonthlyAmt");


            if ((!curr.equalsIgnoreCase("")) && (!recoveryAmt.equalsIgnoreCase(""))) {
                recovery.setCurrency(curr);
                recovery.setRecoveryAmount(recoveryAmt);
                dept.setRecovery(recovery);
            }

            departments.getDepartment().add(dept);
            departmentList.add(departmentHashMap);
        }

        document.setDepartments(departments);
        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration",Boolean.FALSE);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\"?>");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        marshaller.marshal(document,outputStream);
        payload = new String(outputStream.toByteArray());
        Path path = Paths.get(fileLocation+"\\"+fileName);
        try{
            Files.deleteIfExists(path);
        }catch(IOException exception){
            //logger.error(exception.getMessage(),exception);
            exception.printStackTrace();
        }
        try{
            Files.createFile(path);
            Files.write(path,payload.getBytes(),StandardOpenOption.APPEND);
        }catch(IOException exception){
            exception.printStackTrace();
        }


        return filePath;

    }

    public ArrayList<HashMap<String, String>> getDepartmentList() {
        return departmentList;
    }


    public String getFileName() {
        return fileName;
    }



    public String getPayload() {
        return String.format(payload);
    }
}
