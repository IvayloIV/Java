DELIMITER $$
CREATE FUNCTION udf_client_cards_count(name VARCHAR(30))
RETURNS INT
BEGIN
	RETURN (
		SELECT
			COUNT(ca.id)
		FROM clients AS c
		JOIN bank_accounts AS ba
		ON ba.client_id = c.id
		JOIN cards AS ca
		ON ca.bank_account_id = ba.id
		WHERE c.full_name = name
		GROUP BY c.full_name);
END $$

DELIMITER ;
SELECT udf_client_cards_count('Baxy David');