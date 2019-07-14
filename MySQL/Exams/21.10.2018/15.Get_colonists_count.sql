DELIMITER $$
CREATE FUNCTION udf_count_colonists_by_destination_planet(planet_name VARCHAR(30))
RETURNS INT
BEGIN
	RETURN (SELECT COUNT(*)
		FROM planets AS p
		JOIN spaceports AS sp
		ON p.id = sp.planet_id
		JOIN journeys AS j
		ON sp.id = j.destination_spaceport_id
		JOIN travel_cards AS tc
		on j.id = tc.journey_id
		JOIN colonists AS c
		ON tc.colonist_id = c.id
		WHERE p.name = planet_name);
END $$

DELIMITER ;
SELECT udf_count_colonists_by_destination_planet('Otroyphus');