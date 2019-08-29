package com.example.mapping.services;

import com.example.mapping.entries.Employee;
import com.example.mapping.entries.ManagerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public List<ManagerDto> getManagers() {
        ModelMapper modelMapper = new ModelMapper();
        Employee employee1 = new Employee("Pesho", "Peshev", LocalDate.of(2012, 2, 1), BigDecimal.valueOf(23), true, "St");
        Employee employee2 = new Employee("Gosho", "Peshev", LocalDate.of(2012, 2, 1), BigDecimal.valueOf(23), true, "St");
        Employee employee3 = new Employee("Tosho", "Peshev", LocalDate.of(2012, 2, 1), BigDecimal.valueOf(23), true, "St");

        employee1.setEmployees(new ArrayList<Employee>() {{add(employee2); add(employee3);}});
        employee2.setEmployees(new ArrayList<Employee>() {{add(employee3);}});

        List<Employee> employees = new ArrayList<Employee>() {{
            add(employee1);
            add(employee2);
        }};

        List<ManagerDto> managerDtos = new ArrayList<>();
        employees.forEach(e -> managerDtos.add(modelMapper.map(e, ManagerDto.class)));
        return managerDtos;
    }
}
