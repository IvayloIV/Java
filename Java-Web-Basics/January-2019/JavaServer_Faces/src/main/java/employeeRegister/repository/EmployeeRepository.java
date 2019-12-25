package employeeRegister.repository;

import employeeRegister.domain.entities.Employee;

public interface EmployeeRepository extends GenericRepository<Employee, String> {
    public void removeById(String id);

    public Double getTotalSalary();
}
