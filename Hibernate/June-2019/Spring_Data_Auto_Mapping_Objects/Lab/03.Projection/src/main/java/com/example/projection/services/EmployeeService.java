package com.example.projection.services;

import com.example.projection.entities.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    void seedEmployees();

    List<EmployeeDto> getEmployeesBefore1990();
}
