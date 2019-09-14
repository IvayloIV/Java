package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.EmployeeDto;
import softuni.workshop.domain.dtos.EmployeeRootDto;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, FileUtil fileUtil, ValidatorUtil validatorUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importEmployees() throws JAXBException, IOException {
        EmployeeRootDto employeesDto = this.xmlParser.importXML("employees", EmployeeRootDto.class);

        for (EmployeeDto employeeDto : employeesDto.getEmployeeDtos()) {
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            employee.setProject(this.projectRepository.findByName(employeeDto.getProjectDto().getName()));
            List<String> violationsMessage = this.validatorUtil.getViolationsMessage(employee);

            if (violationsMessage.size() > 0) {
                violationsMessage.forEach(System.out::println);
            } else {
                this.employeeRepository.save(employee);
            }
        }
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readContent("employees");
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.findAllByAgeGreaterThan(25);

        employees.forEach(e -> sb.append(String.format("Name: %s%n  Age:%d%n    Project name:%s%n",
                e.getFirstName() + " " + e.getLastName(),
                e.getAge(),
                e.getProject().getName())));

        return sb.toString().trim();
    }
}
