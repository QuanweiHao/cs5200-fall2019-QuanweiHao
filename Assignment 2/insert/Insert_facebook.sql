INSERT INTO `assignment-2`.`website`
(`id`,
`name`,
`description`,
`created`,
`updated`,
`visits`,
`developer`)
VALUES
(123,
'Facebook',
'an onlinne social media and social networking service',
curdate(),
curdate(),
'1234234',
12);

INSERT INTO `assignment-2`.`website_role`
(`id`,
`role`,
`website`,
`developer`)
VALUES
(null,
'owner',
123,
12);

INSERT INTO `assignment-2`.`website_role`
(`id`,
`role`,
`website`,
`developer`)
VALUES
(null,
'editor',
123,
23);

INSERT INTO `assignment-2`.`website_role`
(`id`,
`role`,
`website`,
`developer`)
VALUES
(null,
'admin',
123,
34);


