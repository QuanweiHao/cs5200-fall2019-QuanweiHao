SELECT 
    w.name
FROM
    website w,
    website_privilege wp
WHERE
    wp.developer = 23 AND wp.website = w.id
        AND wp.privilege = 'delete'