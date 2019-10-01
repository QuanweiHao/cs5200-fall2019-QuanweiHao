UPDATE page p,
    (SELECT 
        id
    FROM
        page p
    WHERE
        p.website = 567) t 
SET 
    title = CONCAT('CNET-', title)
WHERE
    p.id = t.id