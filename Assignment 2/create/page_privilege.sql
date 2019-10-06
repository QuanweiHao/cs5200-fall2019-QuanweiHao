CREATE TABLE `page_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page` int(11) NOT NULL,
  `developer` int(11) NOT NULL,
  `privilege` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`page`,`developer`),
  KEY `pagePrivilege_developer_association_idx` (`developer`),
  KEY `pagePrivilege_page_association_idx` (`page`),
  KEY `pagePrivilege_privilege_portEnum_idx` (`privilege`),
  CONSTRAINT `pagePrivilege_developer_association` FOREIGN KEY (`developer`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagePrivilege_page_association` FOREIGN KEY (`page`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagePrivilege_privilege_portEnum` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 