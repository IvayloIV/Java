SELECT
	mc.country_code,
    m.mountain_range,
    p.peak_name,
    p.elevation
FROM peaks AS p
JOIN mountains AS m
ON p.mountain_id = m.id
JOIN mountains_countries AS mc
ON p.mountain_id = mc.mountain_id
	AND mc.country_code = 'BG'
WHERE p.elevation > 2835
ORDER BY p.elevation DESC;