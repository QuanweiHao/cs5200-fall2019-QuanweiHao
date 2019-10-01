SELECT 
    *
FROM
    website w,
    developer d,
    website_role wr
WHERE
    d.id = 34 AND wr.developer = d.id
        AND wr.website = w.id
        AND wr.role = 'admin'
        AND w.visits > 6000000