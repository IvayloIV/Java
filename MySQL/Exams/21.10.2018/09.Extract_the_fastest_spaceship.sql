SELECT
	sh.name AS 'spaceship_name',
    sp.name AS 'spaceport_name'
FROM spaceships AS sh
JOIN journeys AS j
ON j.spaceship_id = sh.id
JOIN spaceports AS sp
ON j.destination_spaceport_id = sp.id
ORDER BY sh.light_speed_rate DESC
LIMIT 1;