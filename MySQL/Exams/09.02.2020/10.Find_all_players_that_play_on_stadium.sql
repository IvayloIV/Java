DELIMITER $$
CREATE FUNCTION udf_stadium_players_count(stadium_name VARCHAR(30))
	RETURNS INTEGER
BEGIN
	DECLARE player_count INTEGER;
    
    SELECT
		count(p.id)
	INTO player_count
	FROM stadiums s
	LEFT JOIN teams t ON t.stadium_id = s.id
	LEFT JOIN players p ON p.team_id = t.id
	WHERE s.name = stadium_name
	GROUP BY s.id;
    
    RETURN player_count;
END$$
DELIMITER ;