DELIMITER $$
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
	START TRANSACTION;
	IF ((SELECT COUNT(*) FROM spaceships AS sh
		WHERE sh.name = spaceship_name) != 1) THEN
		SIGNAL SQLSTATE VALUE '45000'
		  SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
		ROLLBACK;
	ELSE
		UPDATE spaceships
        SET light_speed_rate = light_speed_rate + light_speed_rate_increse
        WHERE name = spaceship_name;
    END IF;
END $$

DELIMITER ;
CALL udp_modify_spaceship_light_speed_rate ('Na pesho koraba', 5);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'USS Templar';