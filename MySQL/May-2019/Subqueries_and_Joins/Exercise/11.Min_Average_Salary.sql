SELECT MIN(avg_salary) AS 'min_average_salary' 
FROM (SELECT AVG(e.salary) AS 'avg_salary'
	FROM employees AS e
	JOIN departments AS d
	ON e.department_id = d.department_id
	GROUP BY d.name) AS avgs;