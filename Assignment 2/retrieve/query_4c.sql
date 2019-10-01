SELECT 
    wi.*
FROM
    single_base_widget wi,
    (SELECT 
        p.id
    FROM
        page p, page_role pr
    WHERE
        pr.developer = 12 and pr.page = p.id and pr.role = 'reviewer') p
WHERE
    wi.page = p.id and wi.dtype = 'image'