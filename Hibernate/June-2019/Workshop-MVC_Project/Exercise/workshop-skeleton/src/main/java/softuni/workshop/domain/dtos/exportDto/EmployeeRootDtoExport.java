package softuni.workshop.domain.dtos.exportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRootDtoExport {
    @XmlElement(name = "employee")
    private List<EmployeeDtoExport> employeesDto;

    public EmployeeRootDtoExport() {
    }

    public List<EmployeeDtoExport> getEmployeesDto() {
        return employeesDto;
    }

    public void setEmployeesDto(List<EmployeeDtoExport> employeesDto) {
        this.employeesDto = employeesDto;
    }
}
