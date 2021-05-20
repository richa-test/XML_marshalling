package payloadgeneration;

import pojos.EmployeePojos.Company;
import pojos.EmployeePojos.Employee;
import pojos.EmployeePojos.Employees;
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

public class EmployeeCreationPayload {


    ArrayList<HashMap<String,String>>  employeeCreationList;


    String payload;
    String fileName;

    public String generateEmployeeCreationPayload(ArrayList<HashMap<String,String>> employeeDataList,String fileLocation) throws Exception{

        employeeCreationList = new ArrayList<HashMap<String,String>>();
        fileName = "EMP"+ DateFunctions.getFormattedCurrentDate("yyyyMMddHHmmSSS")+ DataGenerator.generateRandomLong(6);
        String filePath = fileLocation + "\\" + fileName;
        Employees employees =new Employees();
        employees.setEmployee(new ArrayList<Employee>());


        for(int i = 0;i < employeeDataList.size(); i ++){
            HashMap<String,String> employeeHashMap = employeeDataList.get(i);
            Employee emp =  new Employee();

            //set employee values
            String id = DataGenerator.generateEmployeeId(i);
            employeeHashMap.put("EmployeeID",id);
            emp.setId(id);
            emp.setFirstName(employeeHashMap.get("FirstName"));
            emp.setLastName(employeeHashMap.get("LastName"));
            emp.setIncome(employeeHashMap.get("Income"));
            //set company values
            Company comp = new Company();
            comp.setId(employeeHashMap.get("CompanyId"));
            comp.setName(employeeHashMap.get("CompanyName"));
            emp.setCompany(comp);

            employees.getEmployee().add(emp);
            employeeCreationList.add(employeeHashMap);

        }//close of testdata list loop

        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration",Boolean.FALSE);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\"?>");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        marshaller.marshal(employees,outputStream);
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


    public ArrayList<HashMap<String, String>> getEmployeeCreationList() {
        return employeeCreationList;
    }


    public String getFileName() {
        return fileName;
    }

    public String getPayload() {
        return String.format(payload);
    }

}
