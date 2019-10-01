CREATE DEFINER=`quanwei`@`%` TRIGGER `assignment-2`.`page_role_BEFORE_DELETE` BEFORE DELETE ON `page_role` FOR EACH ROW
BEGIN
	delete from page_privilege
	where developer = old.developer and page = old.page;
END