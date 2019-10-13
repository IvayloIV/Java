UPDATE chats c
SET c.start_date = (SELECT MIN(m.sent_on) FROM messages m
	WHERE m.chat_id = c.id)
WHERE c.start_date > ANY (SELECT m.sent_on FROM messages m
	WHERE m.chat_id = c.id);