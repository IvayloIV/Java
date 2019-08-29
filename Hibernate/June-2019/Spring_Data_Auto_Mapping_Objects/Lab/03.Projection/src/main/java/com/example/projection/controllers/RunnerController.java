package com.example.projection.controllers;

import com.example.projection.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class RunnerController implements CommandLineRunner {
    private final EmployeeService employeeService;

    @Autowired
    public RunnerController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeService.getEmployeesBefore1990()
            .forEach(System.out::println);
    }
}
