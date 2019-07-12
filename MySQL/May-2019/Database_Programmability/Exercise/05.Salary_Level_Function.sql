DELIMITER &&
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE)
RETURNS VARCHAR(20)
BEGIN
	DECLARE result VARCHAR(20);
    SET result := (
		SELECT CASE
			WHEN salary < 30000 THEN 'Low'
			WHEN salary > 50000 THEN 'High'
			ELSE 'Average'
		END);
	RETURN result;
END &&

DELIMITER ;
SELECT ufn_get_salary_level(222222);