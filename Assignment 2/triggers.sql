DELIMITER //
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
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`website_role_BEFORE_UPDATE` BEFORE UPDATE ON `website_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
    set role = new.role;
    
    delete from website_privilege
	where developer = old.developer and website = old.website;
    
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
            where p. id = 'read';
	end case;
		
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`website_role_BEFORE_DELETE` BEFORE DELETE ON `website_role` FOR EACH ROW
BEGIN
	delete from website_privilege
    where developer = old.developer and website = old.website;
END //

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
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`page_role_BEFORE_UPDATE` BEFORE UPDATE ON `page_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
    set role = new.role;
    
    delete from page_privilege
	where developer = old.developer and page = old.page;
    
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
    
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`page_role_BEFORE_DELETE` BEFORE DELETE ON `page_role` FOR EACH ROW
BEGIN
	delete from page_privilege
	where developer = old.developer and page = old.page;
END//

DELIMITER ;


