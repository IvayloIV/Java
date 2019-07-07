SELECT
	e.employee_id,
    e.first_name,
    p.name AS 'project_name'
FROM employees AS e
JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id
WHERE p.start_date >= '2002/08/14'
	AND p.end_date IS NULL
ORDER BY
	e.first_name ASC,
    p.name ASC
LIMIT 5;