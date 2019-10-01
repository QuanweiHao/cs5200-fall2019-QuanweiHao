SELECT 
    p.title
FROM
    page p,
    page_privilege pp
WHERE
    pp.developer = 34 AND pp.page = p.id
        AND pp.privilege = 'create'