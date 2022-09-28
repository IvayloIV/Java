package softuni.course.mapping.service;

import softuni.course.mapping.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee save(Employee employee);

    public List<Employee> getManagers();
}
