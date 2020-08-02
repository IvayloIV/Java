DELIMITER $$
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INTEGER, team_name VARCHAR(45))
BEGIN
	SELECT
		concat(p.first_name, ' ', p.last_name) full_name,
		p.age,
		p.salary,
		sd.dribbling,
		sd.speed,
		t.name
	FROM players p
	JOIN skills_data sd ON sd.id = p.skills_data_id
	JOIN teams t ON t.id = p.team_id
	WHERE t.name = team_name
	  and sd.dribbling > min_dribble_points
	  and sd.speed > (SELECT avg(s.speed) FROM players pl
						JOIN skills_data s ON pl.skills_data_id = s.id)
	ORDER BY sd.speed DESC
	LIMIT 1;
END$$

DELIMITER ;
CALL udp_find_playmaker (20, 'Skyble');