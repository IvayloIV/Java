SELECT
	CONCAT(e.first_name, ' ', e.last_name) AS 'name',
    e.started_on,
    COUNT(ec.client_id) AS count_of_clients
FROM employees_clients AS ec
JOIN employees AS e
ON ec.employee_id = e.id
JOIN clients AS c
ON ec.client_id = c.id
GROUP BY e.first_name, e.last_name
ORDER BY
	count_of_clients DESC,
    e.id ASC
LIMIT 5;