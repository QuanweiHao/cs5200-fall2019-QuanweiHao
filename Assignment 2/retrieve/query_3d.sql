SELECT 
    SUM(p.views) AS total_pageviews
FROM
    page p
WHERE
    p.website = 567