package com.example.projection.services;

import com.example.projection.entities.Employee;
import com.example.projection.entities.EmployeeDto;
import com.example.projection.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void seedEmployees() {
        Employee employee1 = new Employee("Pesho2", "Peshov", BigDecimal.valueOf(23), LocalDate.of(1888, 1, 1), "St");
        Employee employee2 = new Employee("Gosho2", "Peshov", BigDecimal.valueOf(23), LocalDate.of(1888, 1, 1), "St");
        Employee employee3 = new Employee("Tosho2", "Peshov", BigDecimal.valueOf(23), LocalDate.of(1888, 1, 1), "St");

        employee2.setManager(employee1);
        employee3.setManager(employee1);

        this.employeeRepository.save(employee1);
        this.employeeRepository.save(employee2);
        this.employeeRepository.save(employee3);
    }

    public List<EmployeeDto> getEmployeesBefore1990() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        List<Employee> employees = this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(date);

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Employee, EmployeeDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMappings(m -> m.map(s -> s.getManager().getLastName(),
                EmployeeDto::setManagerLastName));

        List<EmployeeDto> employeeDtos = new ArrayList<>();

        employees.forEach(e -> employeeDtos.add(typeMap.map(e)));
        return employeeDtos;
    }
}
