select p.* from page p, developer d, page_role pr
where d.id = 12 and pr.developer = 12 and pr.page = p.id and pr.role = 'editor'