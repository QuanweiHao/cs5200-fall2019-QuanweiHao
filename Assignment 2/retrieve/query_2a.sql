select website.* from website, (select min(visits) as min_visits from website) q
where website.visits = q.min_visits