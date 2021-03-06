DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id VARCHAR(50))
BEGIN
	START TRANSACTION;
	IF((SELECT COUNT(employee_id) FROM employees WHERE employee_id = id) <> 1) THEN
    ROLLBACK;
    ELSE
		UPDATE employees AS e	
		SET salary = salary * 1.05
		WHERE e.employee_id = id;
    END IF;
END $$