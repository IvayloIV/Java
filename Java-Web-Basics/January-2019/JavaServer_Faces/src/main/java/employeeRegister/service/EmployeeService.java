package employeeRegister.service;

import employeeRegister.domain.models.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeServiceModel> getAll();

    public void removeEmployee(String id);

    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    public Double getTotalSalary();
}
