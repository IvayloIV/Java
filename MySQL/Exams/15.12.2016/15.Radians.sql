DELIMITER $$
CREATE FUNCTION udf_get_radians(degrees FLOAT)
RETURNS FLOAT
BEGIN
	return degrees * PI() / 180;
END$$

DELIMITER ;
SELECT udf_get_radians(22.12) AS radians;