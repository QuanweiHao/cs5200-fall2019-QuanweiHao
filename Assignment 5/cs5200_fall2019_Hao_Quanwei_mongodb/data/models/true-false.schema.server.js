const mongoose = require('mongoose')

const TrueFalse = mongoose.Schema({
    _id: Number,
    isTrue: Boolean
});

module.exports = TrueFalse;