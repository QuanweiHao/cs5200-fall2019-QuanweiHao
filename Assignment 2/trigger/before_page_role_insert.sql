CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`page_role_BEFORE_INSERT` BEFORE INSERT ON `page_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
	set role = new.role;
    case role
		when 'owner' then
			insert into page_privilege (developer, page, privilege)
            select new.developer, new.page, p.id
            from privilege p;
		when 'admin' then
			insert into page_privilege (developer, page, privilege)
            select new.developer, new.page, p.id
            from privilege p;
		when 'writer' then
			insert into page_privilege (developer, page, privilege)
            select new.developer, new.page, p.id
            from privilege p
            where p.id <> 'delete';
		when 'editor' then
			insert into page_privilege (developer, page, privilege)
            select new.developer, new.page, p.id
            from privilege p
            where p.id in ('read', 'update');
		else
			insert into page_privilege (developer, page, privilege)
            select new.developer, new.page, p.id
            from privilege p
            where p.id = 'read';
	end case;
END