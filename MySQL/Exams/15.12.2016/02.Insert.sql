INSERT INTO messages(content, sent_on, chat_id, user_id)
SELECT
	CONCAT_WS('-', u.age, u.gender, l.latitude, l.longitude),
    CURDATE(),
    CEIL(CASE u.gender
		WHEN 'F' THEN sqrt(u.age * 2)
        WHEN 'M' THEN pow((u.age / 18), 3)
	END),
    u.id
FROM users u
JOIN locations l ON u.location_id = l.id
WHERE u.id BETWEEN 10 AND 20;