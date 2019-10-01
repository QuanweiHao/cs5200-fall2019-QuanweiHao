SELECT 
    COUNT(w.id) AS number_widgets
FROM
    (SELECT 
        wi.id
    FROM
        single_base_widget wi, 
        (SELECT 
			p.id
		FROM
			page p, website w
		WHERE
			p.website = w.id
            AND w.name = 'wikipedia') p
    WHERE
        wi.page = p.id) w
