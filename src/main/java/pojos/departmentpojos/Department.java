package pojos.departmentpojos;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recovery",propOrder ={
        "departmentNo",
        "departmenName",
        "recovery"
})
public class Department {

    @XmlElement(name = "DepartmentNumber")
    private String departmentNo;
    @XmlElement(name = "DepartmentName")
    private String departmenName;

    @XmlElement(name = "RecoveryMonthlyAmt")
    private Recovery recovery;

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getDepartmenName() {
        return departmenName;
    }

    public void setDepartmenName(String departmenName) {
        this.departmenName = departmenName;
    }

    public Recovery getRecovery() {
        return recovery;
    }

    public void setRecovery(Recovery recovery) {
        this.recovery = recovery;
    }


}
