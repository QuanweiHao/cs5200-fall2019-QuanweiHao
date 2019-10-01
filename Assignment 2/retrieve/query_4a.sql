SELECT 
    wi.*
FROM
    single_base_widget wi,
    (SELECT 
        p.id
    FROM
        website w, page p
    WHERE
        w.id = 567 AND p.website = w.id
            AND p.title = 'home') p
WHERE
    wi.page = p.id