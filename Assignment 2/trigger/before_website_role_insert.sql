CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`website_role_BEFORE_INSERT` 
BEFORE INSERT ON `website_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
	set role = new.role;
    case role
		when 'owner' then
			insert into website_privilege (developer, website, privilege)
            select new.developer, new.website, p.id
            from privilege p;
		when 'admin' then
			insert into website_privilege (developer, website, privilege)
            select new.developer, new.website, p.id
            from privilege p;
		when 'writer' then
			insert into website_privilege (developer, website, privilege)
            select new.developer, new.website, p.id
            from privilege p
            where p. id <> 'delete';
		when 'editor' then
			insert into website_privilege (developer, website, privilege)
            select new.developer, new.website, p.id
            from privilege p
            where p. id in ('read', 'update');
		else
			insert into website_privilege (developer, website, privilege)
            select new.developer, new.website, p.id
            from privilege p
            where p. id= 'read';
	end case;
END