SELECT
	m.id,
    m.chat_id,
    m.user_id
FROM messages m
WHERE m.chat_id = 17
	AND ((m.user_id, m.chat_id) NOT IN (
		SELECT us.user_id, us.chat_id
        FROM users_chats us)
        OR m.user_id IS NULL)
ORDER BY m.id DESC;