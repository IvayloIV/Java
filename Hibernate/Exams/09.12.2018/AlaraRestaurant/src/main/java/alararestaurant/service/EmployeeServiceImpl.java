package alararestaurant.service;

import alararestaurant.domain.dtos.imports.json.EmployeeDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.base.FileUtil;
import alararestaurant.util.base.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidatorUtil validatorUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile("employees.json");
    }

    @Override
    public String importEmployees(String employees) throws IOException {
        StringBuilder sb = new StringBuilder();
        String employeesStr = this.readEmployeesJsonFile();
        EmployeeDto[] employeesDto = this.gson.fromJson(employeesStr, EmployeeDto[].class);

        for (EmployeeDto employeeDto : employeesDto) {
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            Position position = employee.getPosition();
            Position positionDb = this.positionRepository.findByName(position.getName());

            if (!this.validatorUtil.isValid(employee)) {
                sb.append("Invalid data format.")
                    .append(System.lineSeparator());
                continue;
            }

            if (positionDb == null) {
                if (!this.validatorUtil.isValid(position)) {
                    sb.append("Invalid data format.")
                            .append(System.lineSeparator());
                    continue;
                }
                positionDb = this.positionRepository.saveAndFlush(position);
            }

            employee.setPosition(positionDb);
            this.employeeRepository.saveAndFlush(employee);
            sb.append(String.format("Record %s successfully imported.%n", employee.getName()));
        }

        return sb.toString().trim();
    }
}
