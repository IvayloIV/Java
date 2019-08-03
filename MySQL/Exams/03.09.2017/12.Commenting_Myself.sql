SELECT
	u.id,
    u.username,
    COUNT(c.user_id) AS 'my_comments'
FROM users AS u
LEFT JOIN comments AS c
ON c.user_id = u.id
	AND u.id = (
		SELECT p.user_id FROM posts AS p
        WHERE p.id = c.post_id
    )
GROUP BY u.id
ORDER BY 
	my_comments DESC,
    u.id ASC;