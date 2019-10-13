SELECT
	u.nickname,
    c.title,
    l.latitude,
    l.longitude
FROM users u
JOIN locations l ON u.location_id = l.id
JOIN users_chats uc ON u.id = uc.user_id
JOIN chats c ON c.id = uc.chat_id
WHERE (l.latitude BETWEEN 41.1399 AND 44.12999)
	AND (l.longitude BETWEEN 22.2099 AND 28.3599)
ORDER BY c.title ASC;