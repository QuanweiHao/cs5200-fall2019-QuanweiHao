CREATE TABLE `website_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developer` int(11) NOT NULL,
  `website` int(11) NOT NULL,
  `privilege` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`developer`,`website`),
  KEY `websitePrivilege_developer_association_idx` (`developer`),
  KEY `websitePrivilege_website_association_idx` (`website`),
  KEY `websitePrivilege_privilege_portEnum_idx` (`privilege`),
  CONSTRAINT `websitePrivilege_developer_association` FOREIGN KEY (`developer`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websitePrivilege_privilege_portEnum` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websitePrivilege_website_association` FOREIGN KEY (`website`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 