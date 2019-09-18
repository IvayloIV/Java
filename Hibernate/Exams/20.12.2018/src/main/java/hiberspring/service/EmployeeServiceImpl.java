package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.xml.EmployeeRootXmlDto;
import hiberspring.domain.dtos.xml.EmployeeXmlDto;
import hiberspring.domain.entities.Employee;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.base.EmployeeService;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import hiberspring.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile("employees.xml");
    }

    @Override
    public String importEmployees() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        EmployeeRootXmlDto employeesDto = this.xmlParser.importXML("employees", EmployeeRootXmlDto.class);

        for (EmployeeXmlDto employeeXmlDto : employeesDto.getEmployeesDto()) {
            Employee employee = this.modelMapper.map(employeeXmlDto, Employee.class);
            employee.setBranch(this.branchRepository.getByName(employeeXmlDto.getBranch()));
            employee.setEmployeeCard(this.employeeCardRepository.getByNumber(employeeXmlDto.getCard()));

            if (!this.validatorUtil.isValid(employee)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            this.employeeRepository.save(employee);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    "Employee",
                    employee.getFirstName() + " " + employee.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.getProductiveEmployees("Branch");

        for (Employee employee : employees) {
            sb.append(String.format("Name: %s", employee.getFirstName() + " " + employee.getLastName()))
                    .append(System.lineSeparator());

            sb.append(String.format("Position: %s", employee.getPosition()))
                    .append(System.lineSeparator());

            sb.append(String.format("Card Number: %s", employee.getEmployeeCard().getNumber()))
                    .append(System.lineSeparator());

            sb.append("-------------------------")
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
