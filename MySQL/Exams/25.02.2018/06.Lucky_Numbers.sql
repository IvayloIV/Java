SELECT * 
FROM repositories_contributors AS rc
WHERE rc.contributor_id = rc.repository_id
ORDER BY rc.repository_id ASC;