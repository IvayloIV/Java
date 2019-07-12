DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	IF(money_amount < 0 OR money_amount > (
		SELECT a.balance
        FROM accounts AS a
        JOIN account_holders AS ah
		ON a.account_holder_id = ah.id
        WHERE a.id = account_id
    ))
    THEN ROLLBACK;
    ELSE
		UPDATE accounts AS a
		JOIN account_holders AS ah
		ON a.account_holder_id = ah.id
		SET a.balance = a.balance - money_amount
		WHERE a.id = account_id;
    END IF;
END $$

DELIMITER ;
CALL usp_withdraw_money(1, 104);