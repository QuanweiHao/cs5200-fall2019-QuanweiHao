DELETE FROM website_role 
WHERE
    website = 567;

SELECT 
    @page_id:=id
FROM
    page
WHERE
    website = 567;
    
DELETE FROM page_role 
WHERE
    page = @page_id;

DELETE FROM single_base_widget 
WHERE
    page = @page_id;

DELETE FROM page 
WHERE
    website = 567;
    
DELETE FROM website 
WHERE
    id = 567;