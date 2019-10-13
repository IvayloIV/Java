DELIMITER $$
CREATE PROCEDURE udp_send_message(user_id INT(11), chat_id INT(11), content VARCHAR(200))
BEGIN
	IF (SELECT COUNT(*) FROM users_chats us
		WHERE us.chat_id = chat_id AND us.user_id = user_id) = 0 THEN
        SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'There is no chat with that user!';
	ELSE
		INSERT INTO messages (content, sent_on, chat_id, user_id)
			VALUES(content, SYSDATE(), chat_id, user_id);
	END IF;
END$$

DELIMITER ;
CALL udp_send_message(30, 1, 'Awesome');