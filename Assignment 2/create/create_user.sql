CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_aggrement` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)