SELECT 
    wi.*
FROM
    single_base_widget wi,
    (SELECT 
        p.id
    FROM
        page p, website w
    WHERE
        w.id = 456 AND p.website = w.id) p
WHERE
    wi.page = p.id AND wi.dtype = 'youtube'