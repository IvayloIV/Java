SELECT
	max(sd.speed),
    ts.name
FROM teams t
RIGHT JOIN stadiums s ON t.stadium_id = s.id
RIGHT JOIN towns ts ON ts.id = s.town_id
LEFT JOIN players p ON p.team_id = t.id
LEFT JOIN skills_data sd ON p.skills_data_id = sd.id
WHERE t.name != 'Devify'
GROUP BY ts.name
ORDER BY max(sd.speed) DESC, ts.name;