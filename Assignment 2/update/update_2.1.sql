select @page_id := wi.page from single_base_widget wi where wi.id = 345;
 
UPDATE single_base_widget wi 
SET 
    wi.order = 3
WHERE
    wi.id = 345;

UPDATE single_base_widget wi,
    (SELECT 
        id
    FROM
        single_base_widget wi
    WHERE
        wi.page = @page_id) t 
SET 
    wi.order = wi.order - 1
WHERE
    wi.id = t.id