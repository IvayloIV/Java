DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(town_name VARCHAR(50))
BEGIN
	SELECT t.name AS 'town_name'
    FROM towns AS t
    WHERE LOWER(LEFT(t.name, LENGTH(town_name))) = town_name
    ORDER BY t.name ASC;
END $$