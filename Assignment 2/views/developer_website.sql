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
        (`w`.`developer` = `d`.`id`)