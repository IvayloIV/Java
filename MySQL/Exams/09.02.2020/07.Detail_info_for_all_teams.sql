SELECT
	t.name team_name,
    t.established,
    t.fan_base,
    COUNT(p.id) players_count
FROM teams t
LEFT JOIN players p ON t.id = p.team_id
GROUP BY t.id, t.name, t.established, t.fan_base
ORDER BY COUNT(p.id) DESC, t.fan_base DESC;