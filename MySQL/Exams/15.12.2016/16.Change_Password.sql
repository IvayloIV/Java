DELIMITER $$
CREATE PROCEDURE udp_change_password(email VARCHAR(200), new_password VARCHAR(200))
BEGIN
	IF ((SELECT COUNT(*) FROM credentials c
		 WHERE c.email = email) = 0) THEN
         SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'The email does\'t exist!';
	ELSE
		UPDATE credentials c
        SET c.password = new_password
        WHERE c.email = email;
	END IF;
END$$

DELIMITER ;
CALL udp_change_password('abarnes0@sogou.co1m','new_pass');