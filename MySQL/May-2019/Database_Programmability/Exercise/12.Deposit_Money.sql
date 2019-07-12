DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	UPDATE accounts AS a
	JOIN account_holders AS ah
	ON a.account_holder_id = ah.id
	SET a.balance = a.balance + money_amount
	WHERE a.id = account_id;
END $$

DELIMITER ;
CALL usp_deposit_money(1, 10);