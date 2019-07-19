SELECT
	parent.id,
    parent.name AS 'Name',
    CONCAT(parent.size, 'KB') AS 'size'
FROM files AS f
RIGHT JOIN files AS parent
ON f.parent_id = parent.id
WHERE f.parent_id IS NULL
ORDER BY parent.id ASC;