const mongoose = require('mongoose');
const connector = () =>
    mongoose.connect('mongodb://localhost:27017/test', {useNewUrlParser: true});

module.exports = connector










