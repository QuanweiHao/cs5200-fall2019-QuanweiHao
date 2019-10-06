CREATE TABLE `page_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `page` int(11) NOT NULL,
  `developer` int(11) NOT NULL,
  PRIMARY KEY (`id`,`page`,`developer`),
  KEY `pageRole_page_association_idx` (`page`),
  KEY `pageRole_developer_association_idx` (`developer`),
  KEY `pageRole_role_portEnum_idx` (`role`),
  CONSTRAINT `pageRole_developer_association` FOREIGN KEY (`developer`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pageRole_page_association` FOREIGN KEY (`page`) REFERENCES `page` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pageRole_role_portEnum` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 