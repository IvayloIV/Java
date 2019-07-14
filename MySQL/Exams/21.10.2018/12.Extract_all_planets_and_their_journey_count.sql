SELECT
	p.name AS 'planet_name',
    COUNT(*) AS 'journeys_count'
FROM spaceports AS sp
JOIN planets AS p
ON p.id = sp.planet_id
JOIN journeys AS j
ON sp.id = j.destination_spaceport_id
GROUP BY p.name
ORDER BY
	journeys_count DESC,
    planet_name ASC;