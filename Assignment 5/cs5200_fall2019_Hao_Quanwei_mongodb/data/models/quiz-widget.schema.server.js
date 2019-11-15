const mongoose = require('mongoose')
const questionSchema = require('./question.schema.server')

const quizWidget = mongoose.Schema({
    _id: Number,
    wtype: String,
    width: Number,
    height: Number,
    questions: [{type: mongoose.Schema.Types.ObjectId, ref: 'QuestionModel'}]
}, {collection: 'quiz-widget'})

module.exports = quizWidget;
