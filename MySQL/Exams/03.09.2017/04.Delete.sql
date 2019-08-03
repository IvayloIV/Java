DELETE FROM users
WHERE id IN (
	SELECT u.id
    FROM (SELECT * FROM users) AS u
	LEFT JOIN users_followers AS uf
	ON uf.user_id = u.id
	LEFT JOIN users_followers AS ufs
	ON ufs.follower_id = u.id
	WHERE uf.user_id IS NULL
		AND ufs.follower_id IS NULL
);