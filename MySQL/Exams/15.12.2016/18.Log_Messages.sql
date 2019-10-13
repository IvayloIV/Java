CREATE TABLE messages_log 
    LIKE messages;
DELIMITER $$
CREATE TRIGGER messages_log
AFTER DELETE
ON messages
FOR EACH ROW
BEGIN 
	INSERT INTO messages_log(id, content, sent_on, chat_id, user_id)
		VALUES (OLD.id, OLD.content, OLD.sent_on, OLD.chat_id, OLD.user_id);
END$$

DELIMITER ;
DELETE FROM messages
WHERE messages.id = 4;