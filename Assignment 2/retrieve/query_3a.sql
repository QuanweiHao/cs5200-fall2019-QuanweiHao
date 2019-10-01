select p.* from page p, (select max(p.views) as max_views from page p) q
where p.views = q.max_views