package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootXmlDto implements Serializable {
    @XmlElement(name = "employee")
    private List<EmployeeXmlDto> employeesDto;

    public List<EmployeeXmlDto> getEmployeesDto() {
        return employeesDto;
    }

    public void setEmployeesDto(List<EmployeeXmlDto> employeesDto) {
        this.employeesDto = employeesDto;
    }
}
