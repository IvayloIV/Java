DELIMITER $$
CREATE PROCEDURE udp_findbyextension(extension VARCHAR(100))
BEGIN
	SELECT
		 f.id,
		 f.name AS 'caption',
		 CONCAT(f.size, 'KB') AS 'user'
	FROM files AS f
	WHERE SUBSTRING(f.name, LOCATE('.', f.name) + 1) = extension
	ORDER BY f.id;
END $$

DELIMITER ;
CALL udp_findbyextension('html');