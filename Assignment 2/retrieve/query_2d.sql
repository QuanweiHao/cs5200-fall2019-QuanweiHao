select * from website w, developer d, website_role wr
where d.id =12 and wr.website = w.id and wr.developer = d.id and wr.role = 'owner' 