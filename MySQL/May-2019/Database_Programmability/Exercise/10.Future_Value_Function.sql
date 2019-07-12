DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(`sum` DOUBLE, `yearly_interest_rate` DOUBLE, `number of years` INT)
RETURNS DOUBLE
BEGIN
	DECLARE result DOUBLE;
    SET result := `sum` * POW((1 + `yearly_interest_rate`), `number of years`);
    RETURN result;
END $$

DELIMITER ;
SELECT ufn_calculate_future_value(1000, 0.1, 5);