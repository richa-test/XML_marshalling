package pojos.EmployeePojos;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name = "employee")
@XmlType(propOrder = { "id", "firstName", "lastName","income","company" })
public class Employee {
	
	
	    private String id;	    
	    private String firstName;
	    private String lastName;
	    private String income;
	    private Company company;




	    
	    @XmlAttribute
	    public String getId() {
			return id;
		}
	    
	   
		public void setId(String id) {
			this.id = id;
		}
	    @XmlElement
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		@XmlElement
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		@XmlElement
		public String getIncome() {
			return income;
		}
		public void setIncome(String income) {
			this.income = income;
		}
		
		@XmlElement(name="Company")
		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}


}
