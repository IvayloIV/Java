SELECT
	c.id,
    CONCAT(u.username, ' : ', c.content) AS 'full_commment'
FROM comments AS c
JOIN users AS u
ON c.user_id = u.id
ORDER BY c.id DESC;