SELECT
	sh.name,
    sh.manufacturer
FROM colonists AS c
JOIN travel_cards AS tc
ON tc.colonist_id = c.id
JOIN journeys AS j
ON j.id = tc.journey_id
JOIN spaceships AS sh
ON sh.id = j.spaceship_id
WHERE DATEDIFF('2019-01-01', DATE_ADD(c.birth_date, INTERVAL 30 YEAR)) < 0
	AND tc.job_during_journey = 'Pilot'
ORDER BY sh.name;