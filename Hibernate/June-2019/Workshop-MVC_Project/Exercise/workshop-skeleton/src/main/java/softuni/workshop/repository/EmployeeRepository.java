package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.workshop.domain.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByAgeGreaterThan(int age);

    List<Employee> findAllByFirstName(String name);

    @Query("SELECT e FROM Employee e " +
            "WHERE e.project.name = :projectName " +
            "ORDER BY e.age ASC")
    List<Employee> findAllByProjectName(@Param(value = "projectName") String projectName);
}
