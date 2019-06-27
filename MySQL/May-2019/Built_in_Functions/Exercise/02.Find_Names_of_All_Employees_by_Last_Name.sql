SELECT `first_name`, `last_name` FROM `employees`
WHERE LOCATE('ei', LOWER(`last_name`)) >= 1
ORDER BY `employee_id` ASC;