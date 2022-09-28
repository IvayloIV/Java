package softuni.course.mapping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.course.mapping.model.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getAllByManagerIsNull();
}
