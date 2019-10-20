delimiter //
create procedure getUnansweredQuestions()
begin
	SELECT 
		q.text AS qText, COUNT(a.id) AS aNum
	FROM
		Question q,
		`User` u,
		Answer a
	WHERE
		u.personId = q.askedBy
			AND a.postedBy = u.personId
			AND a.correctAnswer = 0
	GROUP BY q.module
	HAVING MAX(aNum);
end//
delimiter ;
