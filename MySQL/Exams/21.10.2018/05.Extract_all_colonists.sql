SELECT
	c.id,
    CONCAT(c.first_name, ' ', c.last_name) AS 'full_name',
    c.ucn
FROM colonists AS c
ORDER BY
	c.first_name ASC,
    c.last_name ASC,
    c.id ASC;