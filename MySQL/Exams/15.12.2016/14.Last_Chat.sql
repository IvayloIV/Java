SELECT 
	c.title,
    m.content
FROM chats c
LEFT JOIN messages m ON m.chat_id = c.id
WHERE c.id = (
	SELECT ch.id FROM chats ch
    ORDER BY ch.start_date DESC
    LIMIT 1)
ORDER BY c.start_date DESC;