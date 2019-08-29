package com.example.mapping_objects.services;

import com.example.mapping_objects.entries.Employee;
import com.example.mapping_objects.entries.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private ModelMapper modelMapper;

    public EmployeeServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    public void simpleMapping() {
        Employee employee = new Employee("Pesho", "Peshev", BigDecimal.valueOf(23), LocalDate.of(2032, 2, 4), "St");
        EmployeeDto eDto = this.modelMapper.map(employee, EmployeeDto.class);
        System.out.println(String.format("%s %s %.2f",
                eDto.getFirstName(),
                eDto.getLastName(),
                eDto.getSalary()));
    }
}
