CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `website` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `page_website_idx` (`website`),
  CONSTRAINT `page_website_composition` FOREIGN KEY (`website`) REFERENCES `website` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 