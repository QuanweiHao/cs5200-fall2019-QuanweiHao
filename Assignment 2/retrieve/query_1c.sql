select p.*, d.developer_key from developer d, person p, website_role wr where d.id = p.id 
	and wr.developer = d.id and wr.website = 234 and wr.role <> 'owner'