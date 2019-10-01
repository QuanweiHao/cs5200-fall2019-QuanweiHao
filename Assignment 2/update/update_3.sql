DELIMITER $$
DROP PROCEDURE IF EXISTS update_title$$
CREATE PROCEDURE update_title (inout id int)
begin
	declare p_id int;
    
	DECLARE finished INTEGER DEFAULT 0;
	
    declare cur_p_id cursor for 
		select p.id 
		from page p where p.website = 567;
	
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;
    
    open cur_p_id;
		shift: loop
			fetch cur_p_id into p_id;
			IF finished = 1 THEN 
				LEAVE shift;
			END IF;
            update page set title = concat('CNET-', title) where id = p_id;
		end loop shift;
	close cur_p_id;
end $$
DELIMITER ;



