SELECT `town_id`, `name` FROM `towns`
WHERE LOWER(LEFT(`name`, 1)) NOT IN ('R', 'B', 'D')
ORDER BY `name` ASC;