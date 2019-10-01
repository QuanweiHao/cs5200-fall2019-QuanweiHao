CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `quanwei`@`%` 
    SQL SECURITY DEFINER
VIEW `assignment-2`.`developer_roles_and_privilege` AS
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
        ((((`assignment-2`.`developer_person` `dpe`
        JOIN `assignment-2`.`developer_website` `dw`)
        JOIN `assignment-2`.`developer_websit_role_privilege` `dwrp`)
        JOIN `assignment-2`.`developer_page` `dpa`)
        JOIN `assignment-2`.`developer_page_role_privilege` `dprp`)
    WHERE
        (((((`dpe`.`id` = `dw`.`id`) = `dwrp`.`id`) = `dpa`.`id`) = `dprp`.`id`)
            AND (`dw`.`website` = `dwrp`.`website`)
            AND (`dpa`.`page` = `dprp`.`page`)
            AND (`dpa`.`website` = `dw`.`website`))