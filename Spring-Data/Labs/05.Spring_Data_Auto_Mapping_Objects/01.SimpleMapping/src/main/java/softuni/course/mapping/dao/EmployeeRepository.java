package softuni.course.mapping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.course.mapping.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
