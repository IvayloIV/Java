DELIMITER $$
CREATE PROCEDURE udp_post(username VARCHAR(30), password VARCHAR(30), caption VARCHAR(50), path VARCHAR(100))
BEGIN
	DECLARE user_pass VARCHAR(30); 
    DECLARE picture_id VARCHAR(30);
    SET user_pass = (SELECT u.password
		FROM users AS u
        WHERE u.username = username);
        
    SET picture_id = (SELECT p.id
		FROM pictures AS p
        WHERE p.path = path);
        
	IF user_pass != password THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Password is incorrect!';
	END IF;
            
	IF picture_id IS NULL THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'The picture does not exist!';
	END IF;
    
	INSERT INTO posts(caption, user_id, picture_id)
    VALUES (caption, 
			(SELECT u.id
			 FROM users AS u
             WHERE u.username = username
            ),
            (SELECT p.id
            FROM pictures AS p
            WHERE p.path = path));
END $$

DELIMITER ;
CALL udp_post('UnderSinduxrein', '4l8nYGTKMW', 'Sofia', 's1rc/folders/resources/images/profile/blocked/bmp/kjOJjKpKh4.bmp');