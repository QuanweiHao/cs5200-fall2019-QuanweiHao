DELIMITER //
CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`website_role_BEFORE_INSERT` 
BEFORE INSERT ON `website_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
	set role = new.role;
    case role
		when 'owner' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p;
		when 'admin' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p;
		when 'writer' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id <> 'delete';
		when 'editor' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id in ('read', 'update');
		else
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id= 'read';
	end case;
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`website_role_BEFORE_UPDATE` BEFORE UPDATE ON `website_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
    set role = new.role;
    
    delete from website_privilege
	where developerId = old.developerId and website = old.websiteId;
    
    case role
		when 'owner' then
            insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p;
		when 'admin' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p;
		when 'writer' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id <> 'delete';
		when 'editor' then
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id in ('read', 'update');
		else
			insert into website_privilege (developerId, websiteId, privilege)
            select new.developerId, new.websiteId, p.id
            from privilege p
            where p. id = 'read';
	end case;
		
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`website_role_BEFORE_DELETE` BEFORE DELETE ON `website_role` FOR EACH ROW
BEGIN
	delete from website_privilege
    where developerId = old.developerId and website = old.websiteId;
END //

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`page_role_BEFORE_INSERT` BEFORE INSERT ON `page_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
	set role = new.role;
    case role
		when 'owner' then
			insert into page_privilege (developerId, pageId, privilege)
            select new.developerId, new.pageId, p.id
            from privilege p;
		when 'admin' then
			insert into page_privilege (developerId, pageId, privilege)
            select new.developerId, new.pageId, p.id
            from privilege p;
		when 'writer' then
			insert into page_privilege (developerId, pageId, privilege)
            select new.developerId, new.pageId, p.id
            from privilege p
            where p.id <> 'delete';
		when 'editor' then
			insert into page_privilege (developerId, pageId, privilege)
            select new.developerId, new.pageId, p.id
            from privilege p
            where p.id in ('read', 'update');
		else
			insert into page_privilege (developerId, pageId, privilege)
            select new.developerId, new.pageId, p.id
            from privilege p
            where p.id = 'read';
	end case;
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`page_role_BEFORE_UPDATE` BEFORE UPDATE ON `page_role` FOR EACH ROW
BEGIN
	declare role varchar(45);
    set role = new.role;
    
    delete from page_privilege
	where developerId = old.developerId and page = old.pageId;
    
	case role
		when 'owner' then
			insert into page_privilege (developerId, pageId, privilege)
			select new.developerId, new.pageId, p.id
			from privilege p;
		when 'admin' then
			insert into page_privilege (developerId, pageId, privilege)
			select new.developerId, new.pageId, p.id
			from privilege p;
		when 'writer' then
			insert into page_privilege (developerId, pageId, privilege)
			select new.developerId, new.pageId, p.id
			from privilege p
			where p.id <> 'delete';
		when 'editor' then
			insert into page_privilege (developerId, pageId, privilege)
			select new.developerId, new.pageId, p.id
			from privilege p
			where p.id in ('read', 'update');
		else
			insert into page_privilege (developerId, pageId, privilege)
			select new.developerId, new.pageId, p.id
			from privilege p
			where p.id = 'read';
	end case;
    
END//

CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment_3_jdbc`.`page_role_BEFORE_DELETE` BEFORE DELETE ON `page_role` FOR EACH ROW
BEGIN
	delete from page_privilege
	where developerId = old.developerId and page = old.pageId;
END//

DELIMITER ;


