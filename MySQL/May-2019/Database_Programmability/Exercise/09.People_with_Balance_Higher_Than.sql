DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(salary DOUBLE)
BEGIN
	SELECT
	ah.first_name,
    ah.last_name
	FROM accounts AS a
	JOIN account_holders AS ah
	ON ah.id = a.account_holder_id
    GROUP BY ah.first_name, ah.last_name
	HAVING SUM(a.balance) > salary
	ORDER BY
		a.id ASC,
		ah.first_name DESC,
		ah.last_name ASC;
END $$

DELIMITER ;
CALL usp_get_holders_with_balance_higher_than(7000);