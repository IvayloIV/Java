package com.example.mapping.controllers;

import com.example.mapping.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BuildController implements CommandLineRunner {
    private final EmployeeService employeeService;

    public BuildController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeService.getManagers()
            .forEach(System.out::println);
    }
}
