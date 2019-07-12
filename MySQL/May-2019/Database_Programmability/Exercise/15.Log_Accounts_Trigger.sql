CREATE TABLE `logs`(
	log_id INT(11) NOT NULL AUTO_INCREMENT,
    account_id INT,
    old_sum DECIMAL(19, 4),
    new_sum DECIMAL(19, 4),
    PRIMARY KEY (log_id)
);

DELIMITER $$
CREATE TRIGGER tr_log_balances
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	INSERT INTO `logs`(account_id, old_sum, new_sum)
    VALUES (OLD.id, OLD.balance, NEW.balance);
END $$