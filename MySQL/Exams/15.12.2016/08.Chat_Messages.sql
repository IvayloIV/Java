SELECT
	c.id,
    c.title,
    m.id
FROM chats c
JOIN messages m
ON m.chat_id = c.id
WHERE m.sent_on < '2012-03-26'
	AND c.title LIKE '%x'
ORDER BY
	c.id ASC,
    m.id ASC;