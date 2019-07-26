DELETE FROM employees
WHERE id IN (
	SELECT e.id 
	FROM (SELECT * FROM employees) AS e
	LEFT JOIN employees_clients AS ec
	ON e.id = ec.employee_id
	WHERE ec.employee_id IS NULL);