UPDATE repositories_contributors AS rc
SET rc.repository_id = (
	SELECT MIN(r.id) FROM repositories AS r
	LEFT JOIN (SELECT * FROM repositories_contributors) AS rc
	ON r.id = rc.repository_id
	WHERE rc.contributor_id IS NULL)
WHERE rc.repository_id = rc.contributor_id;