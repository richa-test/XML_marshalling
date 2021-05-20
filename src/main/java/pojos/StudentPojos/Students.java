package pojos.StudentPojos;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="students")
public class Students {

	private List<Student> student;

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}


	
}
