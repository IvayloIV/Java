SELECT
	p.id,
    p.caption,
	(SELECT COUNT(DISTINCT(c.user_id)) 
     FROM comments AS c
     WHERE c.post_id = p.id) AS 'users'
FROM posts AS p
ORDER BY
	users DESC,
    p.id ASC;