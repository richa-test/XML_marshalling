package TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import EmployeePojos.Company;
import EmployeePojos.Employee;
import EmployeePojos.Employees;
import Utilities.ConfigFileReader;
import Utilities.ExcelUtility;

public class java_xml_exceldata {

	public HashMap<String, String> empTestDataMap ;
	public ExcelUtility excelutil = new ExcelUtility();
	public ConfigFileReader prop=new ConfigFileReader();
	
	@Test
	public void employeesXmlexceldata_Test1() throws IOException{
   
		String filePath = System.getProperty("user.dir")+prop.getPropertyValue("TestData_FileName");
		excelutil.openExcelfile(filePath, "Employeetest");
		
		for(int i = 1; i<= excelutil.getRowCount() ;i++)
		{
			empTestDataMap = excelutil.getEmployeeTestData(i);
		
			Employees employees =new Employees();
			employees.setEmployee(new ArrayList<Employee>());
			Employee emp1 =  new Employee();
			Company comp1 = new Company();
			emp1.setId(empTestDataMap.get("emp1_Employee_id"));
			emp1.setFirstName(empTestDataMap.get("emp1_firstName"));
			emp1.setLastName(empTestDataMap.get("emp1_lastName"));
			emp1.setIncome(empTestDataMap.get("emp1_income"));
			comp1.setId(empTestDataMap.get("emp1_Company_id"));
			comp1.setName(empTestDataMap.get("emp1_Company_Name"));
			emp1.setCompany(comp1);
					
			Employee emp2 =  new Employee();
			Company comp2 = new Company();
			emp2.setId(empTestDataMap.get("emp2_Employee_id"));
			emp2.setFirstName(empTestDataMap.get("emp2_firstName"));
			emp2.setLastName(empTestDataMap.get("emp2_lastName"));
			emp2.setIncome(empTestDataMap.get("emp2_income"));
			comp2.setId(empTestDataMap.get("emp2_Company_id"));
			comp2.setName(empTestDataMap.get("emp2_Company_Name"));
			emp2.setCompany(comp2);
			
			employees.getEmployee().add(emp1);
			employees.getEmployee().add(emp2);
			
				
			String empXmlFilePath = "C:\\seleniumprojects\\XML_JAVA_Project\\src\\test\\xmlfiles\\emp"+i+".xml";
			String employeesXmlTest_1 = Utilities.XmlUtil.marshall(employees,empXmlFilePath);
			System.out.println("Xml Request "+ i);
			System.out.println(employeesXmlTest_1);
		}//end for loop
		
		excelutil.closeExcelfile();
	}
}
