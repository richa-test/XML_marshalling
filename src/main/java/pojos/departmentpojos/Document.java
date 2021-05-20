package pojos.departmentpojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="Document",namespace ="urn:iso:std:iso:20022:tech:xsd:pain.009.001.03")
 @XmlAccessorType(XmlAccessType.FIELD)
public class Document {
    @XmlElement(name = "Departments")
    private Departments departments;

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }





}
