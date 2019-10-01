select @old_order := wi.order, @page_id := wi.page from single_base_widget wi where wi.id = 345;
set @shift := 3 - @old_order; 

DELIMITER $$
DROP PROCEDURE IF EXISTS shift_order$$
CREATE PROCEDURE shift_order ()
begin
	declare wi_id int;
    
	DECLARE finished INTEGER DEFAULT 0;
	
    declare cur_wi_id cursor for 
		select wi.id 
		from single_base_widget wi 
			where wi.page = @page_id and wi.order >= @old_order;
	
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;
    
    open cur_wi_id;
		shift: loop
			fetch cur_wi_id into wi_id;
			IF finished = 1 THEN 
				LEAVE shift;
			END IF;
            update single_base_widget wi
            set wi.order = wi.order + @shift
            where wi.id = wi_id;
		end loop shift;
	close cur_wi_id;
end $$
DELIMITER ;
