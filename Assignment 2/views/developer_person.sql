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
        (`p`.`id` = `d`.`id`)