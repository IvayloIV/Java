package softuni.course.mapping.init;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.course.mapping.model.dto.EmployeeDto;
import softuni.course.mapping.model.dto.ManagerDto;
import softuni.course.mapping.model.entity.Employee;
import softuni.course.mapping.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

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

        Employee manager1 = new Employee("Ivan", "Todorov", 1300d,
                LocalDate.of(2000, 4, 10), "Rakovski 2", true);
        Employee employee1 = new Employee("Petur", "Georgiev", 600d,
                LocalDate.of(2000, 9, 10), "Rakovski 2", true);
        employee1.setManager(manager1);
        Employee savedManager1 = employeeService.save(employee1).getManager();
        Employee employee2 = new Employee("Miroslav", "Ivanov", 800d,
                LocalDate.of(2000, 7, 10), "Rakovski 2", true);
//        employee2.setManager(savedManager1);
        employeeService.save(employee2);

        Employee manager2 = new Employee("Toshoko", "Ivanov", 2700d,
                LocalDate.of(2000, 4, 10), "Rakovski 2", true);
        Employee employee3 = new Employee("Ana", "Miroslavova", 1400d,
                LocalDate.of(2000, 1, 10), "Rakovski 2", true);
        employee3.setManager(manager2);
        employeeService.save(employee3);

        List<Employee> managers = employeeService.getManagers();

        TypeMap<Employee, ManagerDto> typeMap = modelMapper.createTypeMap(Employee.class, ManagerDto.class);
        typeMap.addMapping(Employee::getEmployees, ManagerDto::setSubordinates);
        managers.stream()
            .map(typeMap::map)
            .forEach(System.out::println);
    }
}
