SELECT
	i.repository_id,
    r.name,
    COUNT(i.repository_id) AS 'issues'
FROM repositories AS r
JOIN issues AS i
ON r.id = i.repository_id
GROUP BY i.repository_id
ORDER BY
	issues DESC,
    i.repository_id ASC
LIMIT 5;