package TestCases;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.Test;

import EmployeePojos.Company;

//import javax.xml.bind.Marshaller;

import EmployeePojos.Employee;
import EmployeePojos.Employees;
import StudentPojos.Department;
import StudentPojos.Student;
import StudentPojos.Students;


public class javaToXmlStringTest {

	@Test
	public void employeesXmlTest1(){
		Employees employees =new Employees();
		employees.setEmployee(new ArrayList<Employee>());
		Employee emp1 =  new Employee();
		Company comp1 = new Company();
		emp1.setId("1");
		emp1.setFirstName("Seema");
		emp1.setLastName("Ray");
		emp1.setIncome("100.12");
		comp1.setId("company-id1");
		comp1.setName("company1");
		emp1.setCompany(comp1);
		
		
		Employee emp2 =  new Employee();
		Company comp2 = new Company();
		emp2.setId("2");
		emp2.setFirstName("Walter");
		emp2.setLastName("Collins");
		emp2.setIncome("300.62");
		comp2.setId("company-id2");
		comp2.setName("company2");
		emp2.setCompany(comp2);
		
		employees.getEmployee().add(emp1);
		employees.getEmployee().add(emp2);
		
	//String Documenttype ="xmlns=\"urn:iso:std:iso:20022:tech:xsd:pain.012. 001.03\"";
		String empXmlFilePath = "C:\\AutomationProjects\\XML_marshalling\\XML_marshalling\\src\\test\\xmlfiles\\emp.xml";
		String employeesXmlTest_1 = Utilities.XmlUtil.marshall(employees,empXmlFilePath);
		System.out.println(employeesXmlTest_1);
		
	}
	
	@Test
	public void studentXmlTest1(){
		Students students = new Students();
		students.setStudent(new ArrayList<Student>());
				
		Student student1 = new Student();
		Department dep1 = new Department();		
		student1.setId(1234);
		student1.setFirstName("timba");
		student1.setLastName("gili");
		dep1.setId(01);
		dep1.setName("dept1");
		student1.setDepartment(dep1);
		student1.setHobby(Arrays.asList("Swimming","Playing", "Karate"));
		
		Student student2 = new Student();
		Department dep2 = new Department();		
		student2.setId(3456);
		student2.setFirstName("tony");
		student2.setLastName("davis");
		dep2.setId(02);
		dep2.setName("dept2");
		student2.setDepartment(dep2);
		student2.setHobby(Arrays.asList("Gardening","Cricket", "Gymanistic"));
		
		students.getStudent().add(student1);
		students.getStudent().add(student2);
		
		String stdXmlFilePath = "C:\\seleniumprojects\\XML_JAVA_Project\\src\\test\\xmlfiles\\student.xml";
		String studentXmlTest_1 = Utilities.XmlUtil.marshall(students,stdXmlFilePath);
				
	}
	
}
	
