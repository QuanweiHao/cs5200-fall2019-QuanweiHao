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
            AND (`p`.`id` = `wp`.`privilege`))