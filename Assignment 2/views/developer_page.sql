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
            AND (`p`.`website` = `w`.`id`))