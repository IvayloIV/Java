UPDATE coaches c
SET c.coach_level = c.coach_level + 1
WHERE substr(c.first_name, 1, 1) = 'A'
  AND EXISTS (SELECT 'x'
				FROM players_coaches pc
			   WHERE pc.coach_id = c.id);