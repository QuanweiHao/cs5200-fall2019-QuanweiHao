const studentModel = require('../models/student.model.server')
const answerModel = require('../models/answer.model.server')
const questionModel = require('../models/question.model.server')
const quizWidgetModel = require('../models/quiz-widget.model.server')

const truncateDatabase = () => {
    return answerModel.remove()
        .then(() => studentModel.remove())
        .then(() => questionModel.remove())
        .then(() => quizWidgetModel.remove())
        .catch(err => {
            console.log('truncateDatabase failed');
            console.log(err.message);
        })
};

const populateDatabase = () => {
    return createStudent({
        _id: 123,
        username: 'alice',
        password: 'alice',
        firstName: 'Alice',
        lastName: 'Wonderland',
        gradYear: 2020,
        scholarship: 15000
    })
        .then(newStudent => {
            console.log(newStudent);
            return createStudent({
                _id: 234,
                username: 'bob',
                password: 'bob',
                firstName: 'Bob',
                lastName: 'Hope',
                gradYear: 2021,
                scholarship: 12000
            })
        })
        .then(newStudent => {
            console.log(newStudent);
            return createQuestion({
                _id: 321,
                question: 'Is the following schema valid?',
                points: 10,
                questionType: 'TRUE_FALSE',
                trueFalse: {
                    _id: 321,
                    isTrue: false
                }
            })
        })
        .then(newQuestion => {
            console.log(newQuestion);
            return createQuestion({
                _id: 432,
                question: 'DAO stands for Dynamic Access Object.',
                points: 10,
                questionType: 'TRUE_FALSE',
                trueFalse: {
                    _id: 432,
                    isTrue: false
                }
            })
        })
        .then(newQuestion => {
            console.log(newQuestion);
            return createQuestion({
                _id: 543,
                question: 'What does JPA stand for?',
                points: 10,
                questionType: 'MULTIPLE_CHOICE',
                multipleChoice: {
                    _id: 543,
                    choices: 'Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations',
                    correct: 1
                }
            })
        })
        .then(newQuestion => {
            console.log(newQuestion);
            return createQuestion({
                _id: 654,
                question: 'What does ORM stand for?',
                points: 10,
                questionType: 'MULTIPLE_CHOICE',
                multipleChoice: {
                    _id: 543,
                    choices: 'Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping',
                    correct: 4
                }
            })
        })
        .then(newQuestion => {
            console.log(newQuestion);
            return createAnswer({
                _id: 123,
                trueFalseAnswer: true,
                student: 123,
                question: 321
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 234,
                trueFalseAnswer: false,
                student: 123,
                question: 432
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 345,
                multipleChoiceAnswer: 1,
                student: 123,
                question: 543
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 456,
                multipleChoiceAnswer: 2,
                student: 123,
                question: 654
            })
        })
        .then(newQuestion => {
            console.log(newQuestion);
            return createAnswer({
                _id: 567,
                trueFalseAnswer: false,
                student: 234,
                question: 321
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 678,
                trueFalseAnswer: true,
                student: 234,
                question: 432
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 789,
                multipleChoiceAnswer: 3,
                student: 234,
                question: 543
            })
        })
        .then(newAnswer =>{
            console.log(newAnswer)
            return createAnswer({
                _id: 890,
                multipleChoiceAnswer: 4,
                student: 234,
                question: 654
            })
        })
        .catch(err => {
            console.log('populateDatabase failed');
            console.log(err.message);
        })
};

const createStudent = student =>
    studentModel.create(student);

const deleteStudent = studentId =>
    studentModel.remove({_id: studentId});

const createQuestion = question =>
    questionModel.create(question);

const deleteQuestion = questionId =>
    questionModel.remove({_id: questionId});

const answerQuestion = (studentId, questionId, answer) => {
    return createAnswer(answer)
        .then(updateAnswer(answer._id, {
            student: studentId,
            question: questionId
        }))
};

const deleteAnswer = answerId =>
    answerModel.remove({_id: answerId});

// Student DAO

const findAllStudent = () =>
    studentModel.find();

const findStudentById = studentId =>
    studentModel.findById((studentId));

const updateStudent = (studentId, student) =>
    studentModel.update({_id: studentId}, {$set: student});

// Answer DAO
const createAnswer = answer =>
    answerModel.create(answer);

const findAllAnswer = () =>
    answerModel.find();

const findAnswerById = answerId =>
    answerModel.findById(answerId)

const findAnswerByStudent = studentId =>
    answerModel.find({student: studentId})

const findAnswerByQuestion = questionId =>
    answerModel.find({question: questionId})

const updateAnswer = (answerId, answer) =>
    answerModel.update({_id: answerId}, {$set: answer});

const deleteAnswerByQuestionId = questionId =>
    answerModel.remove({question: questionId});

const deleteAnswerByStudentId = studentId =>
    answerModel.remove({student: studentId});

//Question DAO

const findAllQuestion = () =>
    questionModel.find();

const findQuestionById = questionId =>
    questionModel.findById(questionId);

const updateQuestion = (questionId, question) =>
    questionModel.update({_id: questionId}, {$set: question});

// QuizWidget DAO
const createQuizWidget = quizWidget =>
    quizWidgetModel.create(quizWidget);

const findAllQuizWidget = () =>
    quizWidgetModel.find();

const findQuizWidgetById = quizWidgetId =>
    quizWidgetModel.findById(quizWidgetId);

const updateQuizWidget = (quizWidgetId, quizWidget) =>
    quizWidgetModel.update({_id: quizWidgetId}, {$set: quizWidget});

const deleteQuizWidget = quizWidgetId =>
    quizWidgetModel.remove({_id: quizWidgetId});

module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    deleteQuestion,
    answerQuestion,
    deleteAnswer,
    findAllStudent,
    findStudentById,
    updateStudent,
    findAllAnswer,
    findAnswerById,
    findAnswerByStudent,
    findAnswerByQuestion,
    updateAnswer,
    findAllQuestion,
    findQuestionById,
    updateQuestion,
    createQuizWidget,
    findAllQuizWidget,
    findQuizWidgetById,
    updateQuizWidget,
    deleteQuizWidget,
    deleteAnswerByQuestionId,
    deleteAnswerByStudentId
}



// const userModel = require('../models/student.model.server')
//
// const createUser = user =>
//     userModel.create(user)
//
// const findAllUsers = () =>
//     userModel.find()
//
// const findUserById = userId =>
//     userModel.findById(userId)
//
// const findUserByUsername = username =>
//     userModel.findOne({username: username})
//
// module.exports = {
//     createUser,
//     findAllUsers,
//     findUserById,
//     findUserByUsername
// }

