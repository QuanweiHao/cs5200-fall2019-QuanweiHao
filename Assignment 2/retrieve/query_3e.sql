SELECT 
    (SUM(p.views) / COUNT(p.id)) AS average_pageviews
FROM
    page p
WHERE
    p.website = 345