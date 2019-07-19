DELETE FROM repositories
WHERE id IN (
	SELECT 
		r.id
	FROM (SELECT * FROM repositories) AS r
	LEFT JOIN issues AS i
	ON r.id = i.repository_id
	WHERE i.repository_id IS NULL
);