const Koa = require('koa');
const Router = require('koa-router');
const bodyParser = require('koa-bodyparser');
const app = new Koa();
app.use(bodyParser());

let router = new Router();
const Controller = require('./controller');

router
    .get('/infos', Controller.GetInfo)
    .post('/save', Controller.SaveInfo)
    .get('/delete', Controller.DeleteInfo);


app.use(router.routes()).use(router.allowedMethods());


app.listen(12334);
