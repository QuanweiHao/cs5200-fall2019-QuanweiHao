delimiter //
CREATE PROCEDURE endorsedUsersForWeek(IN start date, IN end date)
BEGIN
	SELECT u.personId, CONCAT(u.first_name, "  ", u.last_name) AS full_name, approved 
	FROM (
				SELECT 
					u.personId, u.first_name, u.last_name, u.approved_user AS approved, 
					sum(CASE WHEN correctAnswer THEN 1 END) as numCorrectAns
				FROM
					Question q,
					`User` u,
					Answer a
				WHERE
					u.personId = q.askedBy
				AND a.postedBy = u.personId
				AND a.postedOn BETWEEN start AND end
				GROUP BY u.personId
				ORDER BY numCorrectAns DESC LIMIT 5
				)
	ORDER BY first_name;
END//
delimiter ;
