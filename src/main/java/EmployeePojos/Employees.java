package EmployeePojos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlNs;

@XmlRootElement(name="employees",namespace="urn:iso:std:iso:20022:tech:xsd:pain.012.001.03")
//@XmlRootElement(name="employees")
public class Employees {
	

	//@XmlElement(name = "employee")
	private List<Employee> employee;

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	


	}


	
	

	

