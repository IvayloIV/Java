DELETE FROM colonists
WHERE id IN (
	SELECT
		c.id
    FROM (SELECT * FROM colonists) AS c
    LEFT JOIN travel_cards AS tc
    ON c.id = tc.colonist_id
    WHERE tc.colonist_id IS NULL
);