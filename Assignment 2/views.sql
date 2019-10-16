CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_person` AS
    SELECT 
        `d`.`id` AS `id`,
        `p`.`first_name` AS `first_name`,
        `p`.`last_name` AS `last_name`,
        `p`.`username` AS `username`,
        `p`.`email` AS `email`
    FROM
        (`assignment-2`.`person` `p`
        JOIN `assignment-2`.`developer` `d`)
    WHERE
        (`p`.`id` = `d`.`id`);
        
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_website` AS
    SELECT 
        `d`.`id` AS `id`,
        `w`.`id` AS `website`,
        `w`.`name` AS `name`,
        `w`.`visits` AS `visits`,
        `w`.`updated` AS `updated`
    FROM
        (`assignment-2`.`website` `w`
        JOIN `assignment-2`.`developer` `d`)
    WHERE
        (`w`.`developer` = `d`.`id`);
        
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_page` AS
    SELECT 
        `d`.`id` AS `id`,
        `w`.`id` AS `website`,
        `p`.`id` AS `page`,
        `p`.`title` AS `title`,
        `p`.`views` AS `views`,
        `p`.`updated` AS `updated`
    FROM
        ((`assignment-2`.`developer` `d`
        JOIN `assignment-2`.`website` `w`)
        JOIN `assignment-2`.`page` `p`)
    WHERE
        ((`w`.`developer` = `d`.`id`)
            AND (`p`.`website` = `w`.`id`));

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_websit_role_privilege` AS
    SELECT 
        `d`.`id` AS `id`,
        `w`.`id` AS `website`,
        `r`.`id` AS `role`,
        `p`.`id` AS `privilege`
    FROM
        (((((`assignment-2`.`developer` `d`
        JOIN `assignment-2`.`website` `w`)
        JOIN `assignment-2`.`website_role` `wr`)
        JOIN `assignment-2`.`website_privilege` `wp`)
        JOIN `assignment-2`.`role` `r`)
        JOIN `assignment-2`.`privilege` `p`)
    WHERE
        ((`wr`.`developer` = `d`.`id`)
            AND (`wr`.`website` = `w`.`id`)
            AND (`wp`.`developer` = `d`.`id`)
            AND (`wp`.`website` = `w`.`id`)
            AND (`r`.`id` = `wr`.`role`)
            AND (`p`.`id` = `wp`.`privilege`));

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_page_role_privilege` AS
    SELECT 
        `d`.`id` AS `id`,
        `pa`.`id` AS `page`,
        `r`.`id` AS `role`,
        `p`.`id` AS `privilege`
    FROM
        (((((`assignment-2`.`developer` `d`
        JOIN `assignment-2`.`page` `pa`)
        JOIN `assignment-2`.`page_privilege` `pp`)
        JOIN `assignment-2`.`page_role` `pr`)
        JOIN `assignment-2`.`role` `r`)
        JOIN `assignment-2`.`privilege` `p`)
    WHERE
        ((`pr`.`developer` = `d`.`id`)
            AND (`pr`.`page` = `pa`.`id`)
            AND (`r`.`id` = `pr`.`role`)
            AND (`pp`.`developer` = `d`.`id`)
            AND (`pp`.`page` = `pa`.`id`)
            AND (`p`.`id` = `pp`.`privilege`));
            
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `developer_roles_and_privilege` AS
    SELECT 
        `dpe`.`id` AS `id`,
        `dpe`.`first_name` AS `first_name`,
        `dpe`.`last_name` AS `last_name`,
        `dpe`.`username` AS `username`,
        `dpe`.`email` AS `email`,
        `dw`.`name` AS `name`,
        `dw`.`visits` AS `visits`,
        `dw`.`updated` AS `website_updated`,
        `dwrp`.`role` AS `website_role`,
        `dwrp`.`privilege` AS `website_privilege`,
        `dpa`.`title` AS `title`,
        `dpa`.`views` AS `views`,
        `dpa`.`updated` AS `page_updated`,
        `dprp`.`role` AS `page_role`,
        `dprp`.`privilege` AS `page_privilege`
    FROM
		(developer_person dpe left join
			(select dw.id, dw.website, dw.name, dw.visits, dw.updated, dwrp.role, dwrp.privilege 
			from developer_website dw, developer_websit_role_privilege dwrp 
            where dw.id = dwrp.id and dw.website = dwrp.website) dwrp 
            on dpe.id = dwrp.id) dwrp,
        (select dp.id, dp.website, dp.title, dp.views, dp.updated, dprp.role, dprp.privilege
			from developer_page dp, developer_page_role_privilege dprp 
            where dp.id = dprp.id and dp.page = dprp.page) dprp
    WHERE
        dwrp.id = dprp.id
        and dwrp.website = dprp.website