SELECT 
    p.*, d.developer_key
FROM
    developer d,
    person p,
    page_role pr,
    (SELECT 
        page.id
    FROM
        page
    WHERE
        page.views < 300000) pa
WHERE
    d.id = p.id AND pr.developer = d.id
        AND pr.page = pa.id
        AND pr.role = 'reviewer'