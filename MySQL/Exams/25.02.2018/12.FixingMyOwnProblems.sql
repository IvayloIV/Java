SELECT
	u.id,
    u.username,
    SUM(IF(u.id = i.assignee_id, 1, 0)) AS 'commits'
FROM commits AS c
RIGHT JOIN users AS u
ON u.id = c.contributor_id
LEFT JOIN issues AS i
ON i.id = c.issue_id
GROUP BY u.username
ORDER BY
	commits DESC,
    u.id ASC;