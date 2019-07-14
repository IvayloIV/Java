SELECT
	j.id,
    p.name AS 'planet_name',
    sp.name AS 'spaceport_name',
    j.purpose AS 'journey_purpose'
FROM spaceports AS sp
JOIN planets AS p
ON p.id = sp.planet_id
JOIN journeys AS j
ON sp.id = j.destination_spaceport_id
ORDER BY (j.journey_end - j.journey_start) ASC
LIMIT 1;