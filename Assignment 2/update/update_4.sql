select @page_id := id from page p where p.website = 567 and p.title = 'CNET-Home';

select @charlie_old_role := pr.role from page_role pr
    where pr.developer = 34 and pr.page = @page_id;

select @bob_old_role := pr.role from page_role pr, 
	(select id from page p where p.website = 567 and p.title = 'CNET-Home') t
    where pr.developer = 23 and pr.page = @page_id;

update page_role
set role = @bob_old_role
where developer = 34 and page = @page_id;

update page_role
set role = @charlie_old_role
where developer = 23 and page = @page_id;