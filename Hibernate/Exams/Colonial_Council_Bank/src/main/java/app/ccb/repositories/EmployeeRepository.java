package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee  e " +
            "WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
    Employee findByFullName(@Param("fullName") String fullName);

    @Query("SELECT e FROM Employee  e " +
            "WHERE SIZE(e.clients) > 0 " +
            "ORDER BY SIZE(e.clients) DESC, " +
            "   e.id ASC")
    List<Employee> findEmployeesByClients();
}
