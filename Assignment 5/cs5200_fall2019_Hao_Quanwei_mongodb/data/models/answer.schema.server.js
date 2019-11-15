const mongoose = require('mongoose')
const StudentModel = require('./student.model.server')
const QuestionModel = require('./question.model.server')

const answer = mongoose.Schema({
    _id: Number,
    trueFalseAnswer: Boolean,
    multipleChoiceAnswer: Number,
    student: {type: Number, ref: 'StudentModel'},
    question: {type: Number, ref: 'QuestionModel'}
}, {collection: 'answers'});

module.exports = answer;