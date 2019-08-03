UPDATE users AS u
SET u.profile_picture_id = (
	SELECT
		COUNT(*)
	FROM users_followers AS uf
	WHERE uf.user_id = u.id
)
WHERE u.profile_picture_id IS NULL;