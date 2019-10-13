DELIMITER $$
CREATE TRIGGER remove_user
BEFORE DELETE
ON users
FOR EACH ROW
BEGIN
	DELETE FROM messages
	WHERE user_id = OLD.id;
    
	DELETE FROM users_chats
	WHERE user_id = OLD.id;
END$$

DELIMITER ;
DELETE FROM users
   WHERE users.id = 1;