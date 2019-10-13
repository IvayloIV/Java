SELECT c.title, c.is_active FROM chats c
WHERE c.is_active = FALSE
	AND LENGTH(c.title) < 5
			OR c.title LIKE '__tl%'
ORDER BY c.title DESC;