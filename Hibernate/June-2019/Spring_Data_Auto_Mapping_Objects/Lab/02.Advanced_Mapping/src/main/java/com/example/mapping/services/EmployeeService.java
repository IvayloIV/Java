package com.example.mapping.services;

import com.example.mapping.entries.ManagerDto;

import java.util.List;

public interface EmployeeService {
    List<ManagerDto> getManagers();
}
