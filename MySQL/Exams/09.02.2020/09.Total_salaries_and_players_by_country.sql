SELECT
	c.name,
    count(p.id) total_count_of_players,
    sum(p.salary) total_sum_of_salaries
FROM countries c
LEFT JOIN towns t ON t.country_id = c.id
LEFT JOIN stadiums s ON s.town_id = t.id
LEFT JOIN teams ts ON ts.stadium_id = s.id
LEFT JOIN players p ON p.team_id = ts.id
GROUP BY c.name
ORDER BY count(p.id) DESC, c.name ASC;