CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_aggrement` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `developer` (
  `id` int(11) NOT NULL,
  `developer_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  `developerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `website_developer_aggregation_idx` (`developerId`),
  CONSTRAINT `website_developer_aggregation` FOREIGN KEY (`developerId`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `websiteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `page_website_idx` (`websiteId`),
  CONSTRAINT `page_website_composition` FOREIGN KEY (`websiteId`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `single_base_widget` (
  `dtype` varchar(45) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `css_class` varchar(45) DEFAULT NULL,
  `css_style` varchar(45) DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT '2',
  `html` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `shareble` tinyint(4) DEFAULT NULL,
  `expandable` tinyint(4) DEFAULT NULL,
  `src` varchar(45) DEFAULT NULL,
  `pageId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `page_idx` (`pageId`),
  CONSTRAINT `widget_page_composition` FOREIGN KEY (`pageId`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street1` varchar(45) DEFAULT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `personId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `person_idx` (`personId`),
  CONSTRAINT `address_person_composition` FOREIGN KEY (`personId`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `personId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `phone_person_composition_idx` (`personId`),
  CONSTRAINT `phone_person_composition` FOREIGN KEY (`personId`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `role` (
  `id` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `privilege` (
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `website_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `websiteId` int(11) NOT NULL,
  `developerId` int(11) NOT NULL,
  PRIMARY KEY (`id`,`websiteId`,`developerId`),
  KEY `websiteRole_role_portEnum_idx` (`role`),
  KEY `wesiteRole_website_association_idx` (`websiteId`),
  KEY `websiteRole_developer_association_idx` (`developerId`),
  CONSTRAINT `websiteRole_developer_association` FOREIGN KEY (`developerId`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websiteRole_role_portEnum` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websiteRole_website_association` FOREIGN KEY (`websiteId`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `website_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerId` int(11) NOT NULL,
  `websiteId` int(11) NOT NULL,
  `privilege` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`developerId`,`websiteId`),
  KEY `websitePrivilege_developer_association_idx` (`developerId`),
  KEY `websitePrivilege_website_association_idx` (`websiteId`),
  KEY `websitePrivilege_privilege_portEnum_idx` (`privilege`),
  CONSTRAINT `websitePrivilege_developer_association` FOREIGN KEY (`developerId`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websitePrivilege_privilege_portEnum` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websitePrivilege_website_association` FOREIGN KEY (`websiteId`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `page_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `pageId` int(11) NOT NULL,
  `developerId` int(11) NOT NULL,
  PRIMARY KEY (`id`,`pageId`,`developerId`),
  KEY `pageRole_page_association_idx` (`pageId`),
  KEY `pageRole_developer_association_idx` (`developerId`),
  KEY `pageRole_role_portEnum_idx` (`role`),
  CONSTRAINT `pageRole_developer_association` FOREIGN KEY (`developerId`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pageRole_page_association` FOREIGN KEY (`pageId`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pageRole_role_portEnum` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `page_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageId` int(11) NOT NULL,
  `developerId` int(11) NOT NULL,
  `privilege` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`pageId`,`developerId`),
  KEY `pagePrivilege_developer_association_idx` (`developerId`),
  KEY `pagePrivilege_page_association_idx` (`pageId`),
  KEY `pagePrivilege_privilege_portEnum_idx` (`privilege`),
  CONSTRAINT `pagePrivilege_developer_association` FOREIGN KEY (`developerId`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagePrivilege_page_association` FOREIGN KEY (`pageId`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagePrivilege_privilege_portEnum` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;




