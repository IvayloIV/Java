SELECT
	p.id,
    p.path,
    p.size
FROM pictures AS p
WHERE p.size > 50000
	AND (p.path LIKE '%jpeg%' OR p.path LIKE '%png%')
ORDER BY p.size DESC;