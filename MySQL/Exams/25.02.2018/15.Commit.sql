DELIMITER $$
CREATE PROCEDURE udp_commit(username VARCHAR(200), password VARCHAR(100), message VARCHAR(200), issue_id INT)
BEGIN
	IF (SELECT COUNT(*)
		FROM users AS u 
        WHERE u.username = username) = 0 THEN
        SIGNAL SQLSTATE '45000'
           SET MESSAGE_TEXT = 'No such user!';
    END IF;
    
    IF (SELECT u.password
		FROM users AS u 
        WHERE u.username = username) != password THEN
        SIGNAL SQLSTATE '45000'
           SET MESSAGE_TEXT = 'Password is incorrect!';
    END IF;
    
    IF (SELECT COUNT(*)
		FROM issues AS i
        WHERE i.id = issue_id) = 0 THEN
        SIGNAL SQLSTATE '45000'
           SET MESSAGE_TEXT = 'The issue does not exist!';
    END IF;
    
    INSERT INTO commits (message, issue_id, repository_id, contributor_id)
    SELECT
		message,
		i.id AS 'issue_id',
		i.repository_id,
		u.id AS 'contributor_id'
	FROM users AS u
	JOIN issues AS i
	ON i.id = issue_id
	WHERE u.username = username;
END $$

DELIMITER ;
CALL udp_commit('WhoDenoteBel', 'ajmISQi*', 'Bla Bla', 2);