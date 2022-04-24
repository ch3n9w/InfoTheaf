//const mongoose = require('mongoose');
//mongoose.connect('mongodb://localhost:27017/myapp');
var mongoose = require('./db');

var Schema = mongoose.Schema;

var InfoSchema = new Schema({
    _id: Number,
   info: Object
});

// OrderSchema.plugin(autoIncrement.plugin, 'Order');
var Info = mongoose.model('Info', InfoSchema);

module.exports = Info;
