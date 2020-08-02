SELECT 
	p.id,
    concat(p.first_name, ' ', p.last_name) full_name,
    p.age,
    p.position,
    p.hire_date
FROM players p
JOIN skills_data sd
ON sd.id = p.skills_data_id
WHERE p.position = 'A'
 AND p.hire_date is null
 AND sd.strength > 50
ORDER BY p.salary, p.age;