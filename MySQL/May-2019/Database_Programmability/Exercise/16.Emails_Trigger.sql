CREATE TABLE notification_emails (
	id INT(11) NOT NULL AUTO_INCREMENT,
    recipient VARCHAR(40),
    subject VARCHAR(40),
    body VARCHAR(100),
    PRIMARY KEY (id)
);

DELIMITER $$
CREATE TRIGGER tr_insert_into_ne
AFTER INSERT
ON logs
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails(recipient, subject, body)
    VALUES (
		NEW.account_id, 
        CONCAT('Balance change for account: ', NEW.account_id),
        CONCAT('On ', date(NOW()), ' your balance was changed from ', NEW.old_sum, ' to ', NEW.new_sum, '.')
        );
END $$