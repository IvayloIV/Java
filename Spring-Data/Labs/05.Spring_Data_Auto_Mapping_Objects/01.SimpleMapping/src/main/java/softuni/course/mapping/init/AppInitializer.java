package softuni.course.mapping.init;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.course.mapping.model.dto.EmployeeDto;
import softuni.course.mapping.model.entity.Employee;
import softuni.course.mapping.service.EmployeeService;

import java.time.LocalDate;

@Component
public class AppInitializer implements CommandLineRunner {

    private EmployeeService employeeService;

    @Autowired
    public AppInitializer(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        final ModelMapper modelMapper = new ModelMapper();

        Employee employee = new Employee("Ivan", "Ivanov", 1300d,
                LocalDate.of(2000, 10, 20), "Samokov 11");
        Employee saved = employeeService.save(employee);
        EmployeeDto employeeDto = modelMapper.map(saved, EmployeeDto.class);
        System.out.println(employeeDto);
    }
}
