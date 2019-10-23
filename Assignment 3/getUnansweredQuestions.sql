delimiter //
create procedure getUnansweredQuestions()
begin
	SELECT 
		pdf.qid AS ID,
		pdf.qtext AS Text,
		pdf.module AS Module,
		MAX(pdf.an) AS AnswerNumber
	FROM
		(SELECT 
			q.text AS qtext,
			q.id AS qid,
			COUNT(a.id) AS an,
			SUM(CASE WHEN correctAnswer THEN 1 END) AS ca,
			q.module
		FROM
				Question q,
				`User` u,
				Answer a
		WHERE
				u.personId = q.askedBy
				AND a.postedBy = u.personId
		GROUP BY q.id
		HAVING ca = 0 OR MAX(a.correctAnswer) IS NULL) AS pdf
	GROUP BY module;
end//
delimiter ;
