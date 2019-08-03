SELECT
	u.id,
    u.username,
    CONCAT(p.size, 'KB') AS 'size'
FROM users AS u
JOIN pictures AS p
ON u.profile_picture_id = p.id
WHERE u.profile_picture_id IN (
	SELECT us.profile_picture_id
    FROM users AS us
    GROUP BY us.profile_picture_id
    HAVING COUNT(us.profile_picture_id) > 1
)
ORDER BY u.id ASC;