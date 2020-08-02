INSERT INTO coaches(first_name, last_name, salary, coach_level)
SELECT
	p.first_name,
    p.last_name, 
    p.salary, 
    length(p.first_name)
FROM players p
WHERE p.age >= 45;