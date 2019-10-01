select p.*, d.developer_key from developer d, person p, page_role pr,
	(select p.id from page p, single_base_widget w where w.id = 123 and p.id = w.page) pa
    where d.id = p.id and 
    pr.developer = d.id and pr.page = pa.id and pr.role = 'writer'