SELECT
	e.employee_id,
    e.first_name,
    e.last_name,
    e.department_id,
    e.salary
FROM employees AS e
LEFT JOIN employees AS m
ON e.manager_id = m.employee_id
WHERE m.employee_id IS NULL;