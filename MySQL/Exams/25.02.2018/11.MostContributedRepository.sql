SELECT
	r.id,
	r.name,
    (SELECT COUNT(*)
	FROM commits AS c
    WHERE c.repository_id = r.id) AS 'commits',
    COUNT(rc.contributor_id) AS 'contributors'
FROM repositories_contributors AS rc
JOIN repositories AS r
ON r.id = rc.repository_id
GROUP BY rc.repository_id
ORDER BY
	contributors DESC,
    r.id ASC
LIMIT 1;