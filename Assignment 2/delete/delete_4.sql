DELETE FROM website_role 
WHERE
    website = 567;
    
DELIMITER $$
DROP PROCEDURE IF EXISTS delete_page$$
CREATE PROCEDURE delete_page ()
begin
	declare p_id int;
    
	DECLARE finished INTEGER DEFAULT 0;
	
    declare cur_p_id cursor for 
		select id 
		FROM
			page
		WHERE
			website = 567;
	
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;
    
    open cur_p_id;
		delete_p: loop
			fetch cur_p_id into p_id;
			IF finished = 1 THEN 
				LEAVE delete_p;
			END IF;
			delete from page_role
            where page = p_id;
            
            delete from single_base_widget
            where page = p_id;
		end loop delete_p;
	close cur_p_id;
end $$
DELIMITER ;

call delete_page;

DELETE FROM page 
WHERE
    website = 567;
    
DELETE FROM website 
WHERE
    id = 567;