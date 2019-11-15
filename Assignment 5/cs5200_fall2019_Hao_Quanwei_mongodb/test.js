const connector = require('./data/db')
const universityDao = require('./data/daos/university.dao.server')

connector()

testStudentsInitialCount = () => {
    return universityDao.findAllStudent()
        .then(allStudents => console.log('Total number of students: ' + allStudents.length))
}

testQuestionsInitialCount = () => {
    return universityDao.findAllQuestion()
        .then(allQuestions => console.log('Total number of questions: ' + allQuestions.length))
}

testAnswersInitialCount = () => {
    return universityDao.findAllAnswer()
        .then(allAnswers => console.log('Total number of answers: ' + allAnswers.length))
}

testDeleteAnswer = () => {
    return universityDao.deleteAnswer(890)
        .then(() => {
            return universityDao.findAllAnswer()
        })
        .then(allAnswers => {
            console.log('After delete answer, current total number of answers: ' + allAnswers.length)
            return universityDao.findAnswerByStudent(234)
        })
        .then(answersOfBob => {
            console.log('Bob\' total number of answers:' + answersOfBob.length)
        })
}

testDeleteQuestion = () => {
    return universityDao.deleteAnswerByQuestionId(321)
        .then(() => universityDao.deleteQuestion(321))
        .then(() => {
            return universityDao.findAllQuestion()
        })
        .then(allQuestions =>
            console.log('After delete question, current total number of questions: ' + allQuestions.length)
        )
}

testDeleteStudent = () => {
    return universityDao.deleteAnswerByStudentId(234)
        .then(() => universityDao.deleteStudent(234))
        .then(() => {
            return universityDao.findAllStudent()
        })
        .then(allStudents =>
            console.log('After delete student, current total number of students: ' + allStudents.length)
        )
}



universityDao.truncateDatabase()
    .then(() =>
        universityDao.populateDatabase()
    )
    .then(() =>
        testStudentsInitialCount()
    )
    .then(() =>
        testQuestionsInitialCount()
    )
    .then(() =>
        testAnswersInitialCount()
    )
    .then(() =>
        testDeleteAnswer()
    )
    .then(() =>
        testDeleteQuestion()
    )
    .then(() =>
        testDeleteStudent()
    )