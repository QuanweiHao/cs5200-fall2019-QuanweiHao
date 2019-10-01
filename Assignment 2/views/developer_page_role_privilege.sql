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
            AND (`p`.`id` = `pp`.`privilege`))