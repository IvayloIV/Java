DELIMITER $$
CREATE FUNCTION get_account_balance(account_id INT)
RETURNS DECIMAL(19, 4)
BEGIN
	DECLARE result DECIMAL(19, 4);
    SET result := (SELECT a.balance
    FROM accounts AS a
    WHERE a.id = account_id);
    RETURN RESULT;
END $$

CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	IF (amount < 0 OR
		get_account_balance(to_account_id) IS NULL OR
        get_account_balance(from_account_id) IS NULL OR
		from_account_id = to_account_id OR
        get_account_balance(from_account_id) < amount        
		) THEN ROLLBACK;
	ELSE
		UPDATE accounts AS a
		SET a.balance = get_account_balance(from_account_id) - amount
		WHERE a.id = from_account_id;
		UPDATE accounts AS a
		SET a.balance = get_account_balance(to_account_id) + amount
		WHERE a.id = to_account_id;
    END IF;
END $$

DELIMITER ;
CALL usp_transfer_money(2, 3, 1000);