package pojos.departmentpojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.NONE)
public class Recovery {

    @XmlAttribute(name ="Currency")
    private String currency;
    @XmlValue
    private String recoveryAmount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRecoveryAmount() {
        return recoveryAmount;
    }

    public void setRecoveryAmount(String recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }
}
