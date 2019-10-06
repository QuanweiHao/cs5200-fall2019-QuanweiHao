CREATE TABLE `website_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `website` int(11) NOT NULL,
  `developer` int(11) NOT NULL,
  PRIMARY KEY (`id`,`website`,`developer`),
  KEY `websiteRole_role_portEnum_idx` (`role`),
  KEY `wesiteRole_website_association_idx` (`website`),
  KEY `websiteRole_developer_association_idx` (`developer`),
  CONSTRAINT `websiteRole_developer_association` FOREIGN KEY (`developer`) REFERENCES `developer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websiteRole_role_portEnum` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `websiteRole_website_association` FOREIGN KEY (`website`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 