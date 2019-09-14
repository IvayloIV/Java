package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.exportDto.EmployeeDtoExport;
import softuni.workshop.domain.dtos.exportDto.EmployeeRootDtoExport;
import softuni.workshop.domain.dtos.importDto.EmployeeDto;
import softuni.workshop.domain.dtos.importDto.EmployeeRootDto;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.EmployeeJsonDto;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.GsonUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final GsonUtil gsonUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, FileUtil fileUtil, ValidatorUtil validatorUtil, XmlParser xmlParser, ModelMapper modelMapper, GsonUtil gsonUtil) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.gsonUtil = gsonUtil;
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
        return this.fileUtil.readFile("/xmls/employees.xml");
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

    @Override
    public void exportEmployees() throws JAXBException, IOException {
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeDtoExport> employeeDtoExports = employees
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeDtoExport.class))
                .collect(Collectors.toList());

        EmployeeRootDtoExport employeeRootDtoExport = new EmployeeRootDtoExport();
        employeeRootDtoExport.setEmployeesDto(employeeDtoExports);
        this.xmlParser.exportXML("exported-employees", employeeRootDtoExport);
    }

    @Override
    public void exportEmployeesToJson() throws IOException {
        List<Employee> employees = this.employeeRepository.findAll();
        EmployeeJsonDto[] employeeJsonDtos = employees
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeJsonDto.class))
                .toArray(EmployeeJsonDto[]::new);

        this.gsonUtil.saveJSONtoFile("employees", employeeJsonDtos);
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile("jsons/employees.json");
    }

    @Override
    public boolean areExported() throws IOException {
       return  this.readEmployeesJsonFile().length() > 0;
    }

    @Override
    public String exportEmployeesWithGivenName() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.findAllByFirstName("Mihail");
        employees.forEach(e -> sb.append(String.format("Full name: %s%n Age: %d%n",
                e.getFirstName() + " " + e.getLastName(),
                e.getAge())));

        return sb.toString().trim();
    }

    @Override
    public String exportEmployeesWithGivenProjectName() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.findAllByProjectName("GitBuh_Project");

        employees.forEach(e -> sb.append(String.format("Full name: %s%n Age: %d%n Project name: %s%n",
                e.getFirstName() + " " + e.getLastName(),
                e.getAge(),
                e.getProject().getName())));

        return sb.toString().trim();
    }
}
