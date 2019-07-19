SELECT
	LEFT(f1.name, LOCATE('.', f1.name) - 1) AS 'file',
    (SELECT COUNT(*) FROM commits AS c
     WHERE c.message LIKE CONCAT('%', f1.name, '%')) AS 'recursive_count'
FROM files AS f1
JOIN files AS f2
ON f1.parent_id = f2.id
	AND f2.parent_id = f1.id
    AND f1.id != f2.id
JOIN commits AS c
ON f1.commit_id = c.id
ORDER BY file ASC;