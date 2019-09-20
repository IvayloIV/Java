package app.ccb.services;

import app.ccb.domain.dtos.json.EmployeeDto;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.base.FileUtil;
import app.ccb.util.base.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile("json/employees.json");
    }

    @Override
    public String importEmployees(String employees) throws IOException {
        StringBuilder sb = new StringBuilder();
        String employeesStr = this.readEmployeesJsonFile();
        EmployeeDto[] employeeDtos = this.gson.fromJson(employeesStr, EmployeeDto[].class);

        for (EmployeeDto employeeDto : employeeDtos) {
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            String[] names = employeeDto.getFullName().split("\\s+");
            employee.setFirstName(names[0]);
            employee.setLastName(names[1]);
            employee.setBranch(branchRepository.findByName(employeeDto.getBranchName()));

            if (!this.validatorUtil.isValid(employee)) {
                sb.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
            }

            this.employeeRepository.saveAndFlush(employee);
            sb.append(String.format("Successfully imported %s - %s.%n",
                    "Employee",
                    employee.getFirstName() + " " + employee.getLastName()));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.findEmployeesByClients();

        for (Employee employee : employees) {
            sb.append(String.format("Name: %s Salary: %.2f",
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getSalary()))
                .append(System.lineSeparator());

            sb.append("Clients count: ")
                    .append(employee.getClients().size())
                    .append(System.lineSeparator());

            employee.getClients().forEach(c ->
                    sb.append(String.format("\tClient: %s %d",
                            c.getFullName(),
                            c.getAge()))
                        .append(System.lineSeparator()));
        }

        return sb.toString().trim();
    }
}
