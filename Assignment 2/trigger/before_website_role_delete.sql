CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`website_role_BEFORE_DELETE` BEFORE DELETE ON `website_role` FOR EACH ROW
BEGIN
	delete from website_privilege
    where developer = old.developer and website = old.website;
END