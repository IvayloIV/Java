SELECT
	u.id,
    u.username,
	(SELECT
		p.caption
	FROM posts AS p
	LEFT JOIN comments AS c
	ON c.post_id = p.id
	WHERE p.user_id = u.id
	GROUP BY p.id
    ORDER BY
		COUNT(c.content) DESC,
        p.id ASC
	LIMIT 1) AS 'post'
FROM users AS u
HAVING post IS NOT NULL
ORDER BY u.id ASC;