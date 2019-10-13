DELETE FROM locations
WHERE NOT EXISTS (SELECT 'X' FROM users u
	WHERE u.location_id = locations.id);