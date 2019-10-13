SELECT
	u.nickname,
    c.email,
    c.password
FROM users u
JOIN credentials c ON c.id = u.credential_id
WHERE c.email LIKE '%co.uk'
ORDER BY c.email ASC;