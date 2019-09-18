package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e " +
            "WHERE e.branch.name LIKE CONCAT('%', :branch) AND " +
            "   SIZE(e.branch.products) > 0 " +
            "ORDER BY CONCAT(e.firstName, ' ', e.lastName) ASC, " +
            "   LENGTH(e.position) DESC")
    List<Employee> getProductiveEmployees(@Param("branch") String branch);
}
