select @old_order := wi.order, @page_id := wi.page from single_base_widget wi where wi.id = 345;
set @shift := 3 - @old_order; 

UPDATE single_base_widget wi,
    (SELECT 
        id
    FROM
        single_base_widget wi
    WHERE
        wi.page = @page_id
            AND wi.order >= @old_order) t 
SET 
    wi.order = wi.order + @shift
WHERE
    wi.id = t.id