UPDATE employees_clients AS ec
SET ec.employee_id = (
	SELECT
		ecs.employee_id
	FROM (SELECT * FROM employees_clients) AS ecs
	GROUP BY ecs.employee_id
	ORDER BY
		COUNT(ecs.client_id) ASC,
		ecs.employee_id ASC
	LIMIT 1)
WHERE ec.employee_id = ec.client_id;