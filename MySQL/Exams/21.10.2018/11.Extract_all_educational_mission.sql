SELECT
	p.name AS 'planet_name',
    sp.name AS 'spaceport_name'
FROM spaceports AS sp
JOIN planets AS p
ON p.id = sp.planet_id
JOIN journeys AS j
ON sp.id = j.destination_spaceport_id
WHERE j.purpose = 'Educational'
ORDER BY sp.name DESC;