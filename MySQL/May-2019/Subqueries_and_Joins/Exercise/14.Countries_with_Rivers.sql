SELECT
	c.country_name,
    r.river_name
FROM rivers AS r
JOIN countries_rivers AS cr
ON r.id = cr.river_id
RIGHT JOIN countries AS c
ON cr.country_code = c.country_code
JOIN continents
ON c.continent_code = continents.continent_code
	AND continents.continent_name = 'Africa'
ORDER BY c.country_name ASC
LIMIT 5;