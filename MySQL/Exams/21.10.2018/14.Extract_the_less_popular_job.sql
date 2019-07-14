SELECT
	tc.job_during_journey
FROM travel_cards AS tc
JOIN journeys AS j
ON tc.journey_id = j.id
WHERE j.id = (
	SELECT jor.id
    FROM journeys AS jor
    ORDER BY DATEDIFF(jor.journey_end, jor.journey_start) DESC
    LIMIT 1
)
GROUP BY tc.job_during_journey
ORDER BY COUNT(*) ASC
LIMIT 1;