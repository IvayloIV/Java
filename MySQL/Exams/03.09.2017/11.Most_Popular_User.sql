SELECT
	u.id,
    u.username,
    (SELECT COUNT(*) FROM posts AS p
     WHERE p.user_id = u.id) AS 'posts',
     COUNT(uf.follower_id) AS 'followers'
FROM users_followers AS uf
JOIN users AS u
ON uf.user_id = u.id
GROUP BY uf.user_id
ORDER BY followers DESC
LIMIT 1;