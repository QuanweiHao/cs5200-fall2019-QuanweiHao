select @last_updated := max(p.updated) from page p where p.website = 345;
select @delete_page_id := id from page p where updated = @last_updated;

delete from single_base_widget where page = @delete_page_id;
delete from page_role where page = @delete_page_id;
delete from page where id = @delete_page_id;