SELECT 
    @highest_order:=MAX(wi.order)
FROM
    single_base_widget wi
WHERE
    wi.page = 345;

delete from single_base_widget where page = 345 and single_base_widget.order = @highest_order