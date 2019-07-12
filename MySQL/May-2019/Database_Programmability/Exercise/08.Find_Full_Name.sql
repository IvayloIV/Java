DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT
		CONCAT(a.first_name, ' ', a.last_name) AS 'full_name'
	FROM account_holders AS a
	ORDER BY
		full_name ASC,
        a.id ASC;
END $$

DELIMITER ;
CALL usp_get_holders_full_name();