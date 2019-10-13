SELECT c.id, COUNT(m.id) total_messages FROM messages m
LEFT JOIN chats c ON m.chat_id = c.id
WHERE m.id < 90
GROUP BY c.id
ORDER BY total_messages DESC, c.id ASC
LIMIT 5;