const mongoose = require('mongoose')

const MutipleChoice = mongoose.Schema({
    _id: Number,
    choices: String,
    correct: Number
});

module.exports = MutipleChoice;
