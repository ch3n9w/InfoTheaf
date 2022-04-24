const mongoose = require('mongoose');
const db = mongoose.connection;

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/th1f',{useNewUrlParser: true});

db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function(){
    console.log('mongodb connection success!');
});

module.exports = mongoose
