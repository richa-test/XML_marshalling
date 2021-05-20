package TestCases;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.testng.annotations.Test;

import pojos.EmployeePojos.Employee;
import pojos.EmployeePojos.Employees;
import pojos.StudentPojos.Students;

public class xmlStringToJavaTest {
	
	@Test
	public  void unMarshalingEmployeeTest1() throws JAXBException 
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    //xml file having the xml string
	    Employees emps = (Employees) jaxbUnmarshaller.unmarshal( new File("C:\\seleniumprojects\\XML_JAVA_Project\\src\\test\\xmlfiles\\emp.xml") );
	     
	    for(Employee emp : emps.getEmployee())
	    {
	        System.out.println(emp.getId());
	        System.out.println(emp.getFirstName());
	        System.out.println(emp.getLastName());
	        System.out.println(emp.getCompany().getName());
	        System.out.println(emp.getCompany().getId());
	    }
	}
	    
	    @Test
		public  void unMarshalingStudentTest1() throws JAXBException 
		{
		    JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		     
		    //xml file having the xml string
		    Students stds = (Students) jaxbUnmarshaller.unmarshal( new File("C:\\seleniumprojects\\XML_JAVA_Project\\src\\test\\xmlfiles\\student.xml") );
		    int no_of_students = stds.getStudent().size();
		    
		    for(int i =0;i<no_of_students;i++)
		    {
		    	 System.out.println("Id of student "+(i+1)+" is "+ stds.getStudent().get(i).getId());
				    System.out.println("FirstName of student "+(i+1)+" is "+ stds.getStudent().get(i).getFirstName());
				    System.out.println("LastName of student "+(i+1)+" is "+ stds.getStudent().get(i).getLastName());
				    System.out.println("Depatment Id of student "+(i+1)+" is "+ stds.getStudent().get(i).getDepartment().getId());
				    System.out.println("Department name of student "+(i+1)+" is "+ stds.getStudent().get(i).getDepartment().getName());
				    System.out.println("hobby 1 of student "+(i+1)+" is "+ stds.getStudent().get(i).getHobby().get(0));
				    System.out.println("hobby 2 of student "+(i+1)+" is "+ stds.getStudent().get(i).getHobby().get(1));
				    System.out.println("hobby 3 of student "+(i+1)+" is "+ stds.getStudent().get(i).getHobby().get(2));
				    System.out.println("list of hobbies of student "+(i+1)+" is "+ stds.getStudent().get(i).getHobby());
		    }

		    		    
	}

}
