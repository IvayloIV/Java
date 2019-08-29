package com.example.mapping_objects.controllers;

import com.example.mapping_objects.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {
    private final EmployeeService employeeService;

    @Autowired
    public AppController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeService.simpleMapping();
    }
}
