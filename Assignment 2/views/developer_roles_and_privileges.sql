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