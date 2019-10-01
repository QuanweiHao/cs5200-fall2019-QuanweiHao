SELECT 
    *
FROM
    website w,
    (SELECT 
        p.website, p.id
    FROM
        page p, single_base_widget w
    WHERE
        w.dtype = 'youtube' AND w.page = p.id) pe,
    page_role pr
WHERE
    w.id = pe.website AND pr.developer = 23
        AND pr.page = pe.id
        AND pr.role = 'reviewer'