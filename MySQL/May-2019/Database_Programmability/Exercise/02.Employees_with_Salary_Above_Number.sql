DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary DOUBLE)
BEGIN
	SELECT
		e.first_name,
        e.last_name
    FROM employees e
    WHERE e.salary >= salary
    ORDER BY
		e.first_name ASC,
        e.last_name ASC,
        e.employee_id ASC;
END $$