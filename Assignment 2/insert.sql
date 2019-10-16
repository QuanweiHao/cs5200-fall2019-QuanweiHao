INSERT INTO `assignment-2`.`person`
(`id`,
`first_name`,
`last_name`,
`username`,
`password`,
`email`
)
VALUES
(12, 'Alice', 'Wonder', 'alice', 'alice', 'alice@wonder.com'),
(23, 'Bob', 'Marley', 'bob', 'bob', 'bob@marley.com'),
(34, 'Charles', 'Garciar', 'charlie', 'charlie', 'chuch@garcia.com'),
(45, 'Dan', 'Martin', 'dan', 'dan', 'dan@martin.com'),
(56, 'Ed', 'Karaz', 'ed', 'ed', 'ed@kar.com');

INSERT INTO `assignment-2`.`developer`
(`id`,
`developer_key`)
VALUES
(12, '4321rewq'),
(23, '5432trew'),
(34, '6543ytre');

INSERT INTO `assignment-2`.`user`
(`id`)
VALUES
(45),
(56);

INSERT INTO `assignment-2`.`website`
(`id`,
`name`,
`description`,
`created`,
`updated`,
`visits`,
`developer`)
VALUES
(123, 'Facebook', 'an onlinne social media and social networking service', curdate(), curdate(), '1234234', 12),
(234, 'Twitter', 'an online news and social networking service', curdate(), curdate(), '4321543', 23),
(345, 'Wikipedia', 'a free online encyclopedia', curdate(), curdate(), '345654', 34),
(456, 'CNN', 'an American basic cable and satellite television news channel', curdate(), curdate(), '654345', 12),
(567, 'CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology annd consumer electronics', 
	curdate(), curdate(), '5433455', 23),
(678, 'Gizmodo', 'a design, technology, science and science fiction website that also write articles on politics',
	curdate(), curdate(), '432345', 34);
    
INSERT INTO `assignment-2`.`website_role`
(`id`,
`role`,
`website`,
`developer`)
VALUES
(null, 'owner', 123, 12), (null, 'editor', 123, 23), (null, 'admin', 123, 34),
(null, 'owner', 234, 23), (null, 'editor', 234, 34), (null, 'admin', 234, 12),
(null, 'owner', 345, 34), (null, 'editor', 345, 12), (null, 'admin', 345, 23),
(null, 'owner', 456, 12), (null, 'editor', 456, 23), (null, 'admin', 456, 34),
(null, 'owner', 567, 23), (null, 'editor', 567, 34), (null, 'admin', 567, 12),
(null, 'owner', 678, 34), (null, 'editor', 678, 12), (null, 'admin', 678, 23);

INSERT INTO `assignment-2`.`page`
(`id`,
`title`,
`description`,
`created`,
`updated`,
`views`,
`website`)
VALUES
(123, 'Home', 'Landing page', curdate(), curdate(), 123434, 567),
(234, 'About', 'Website description', curdate(), curdate(), 234545, 678),
(345, 'Contact', 'Addresses, phones, and contact info', curdate(), curdate(), 345656, 345),
(456, 'Preferennces', 'Where users can configure their preferences', curdate(), curdate(), 456776, 456),
(567, 'Profile', 'Users can configure their personal information', curdate(), curdate(), 567878, 567);

INSERT INTO `assignment-2`.`page_role`
(`id`,
`role`,
`page`,
`developer`)
VALUES
(null, 'editor', 123, 12), (null, 'reviewer', 123, 23), (null, 'writer', 123, 34),
(null, 'editor', 234, 23), (null, 'reviewer', 234, 34), (null, 'writer', 234, 12),
(null, 'editor', 345, 34), (null, 'reviewer', 345, 12), (null, 'writer', 345, 23),
(null, 'editor', 456, 12), (null, 'reviewer', 456, 23), (null, 'writer', 456, 34),
(null, 'editor', 567, 23), (null, 'reviewer', 567, 34), (null, 'writer', 567, 12);

INSERT INTO `assignment-2`.`single_base_widget`
(`dtype`,
`id`,
`name`,
`text`,
`order`,
`page`)
VALUES
('heading', 123, 'head123', 'Welcome', 0, 123),
('html', 234, 'post234', '<p>Lorem</p>', 0, 234),
('heading', 345, 'head345', 'Hi', 1, 345),
('html', 456, 'intro456', '<h1>Hi</h1>',  345),
('image', 567, 'image345', 50, 100, 3, 'img/567.png', 345),
('youtube', 678, 'video456', 400, 400, 0, 'https://youtube.be/h67VX51QXiQ', 456);

INSERT INTO `assignment-2`.`phone`
(`id`,
`phone`,
`primary`,
`person`)
VALUES
(null,'123-234-3456', 1, 12),
(null, '234-345-4566', 0, 12),
(null, '345-456-5677', 1, 23),
(null, '321-432-5435', 1, 34),
(null, '432-432-5433', 0, 34),
(null, '543-543-6544', 0, 34);

INSERT INTO `assignment-2`.`address`
(`id`,
`street1`,
`city`,
`zip`,
`primary`,
`person`)
VALUES
(null, '123 Adam St.', 'Alton',  1, 12),
(null, '234 Birch St.', 'Boston', '02345', 0, 12),
(null, '345 Charles St.', 'Chelms', '03455', 1, 23),
(null, '456 Down St.',  '04566', 0, 23),
(null, '543 East St.', 'Everett', '01112', 0, 23),
(null, '654 Frank St.', 'Foulton', '04322', 1, 34);











