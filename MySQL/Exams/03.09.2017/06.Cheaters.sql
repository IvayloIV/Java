SELECT
	u.id,
    u.username
FROM users AS u
JOIN users_followers AS uf
ON uf.follower_id = u.id
	AND uf.user_id = u.id
ORDER BY u.id;