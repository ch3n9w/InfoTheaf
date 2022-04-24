const dao = require('./dao');
const bodyParser = require('koa-bodyparser');

module.exports = {
    GetInfo: async ctx => {
        try {
            var infos = await dao.GetInfo();
            ctx.body = {
                code: '001',
                infos: infos
            };
            console.log(infos)
        } catch (e) {
            console.log(e);
        }
    },

    SaveInfo:  async ctx => {
        try {
	    console.log('hello');
	    console.log(ctx.request.body);
            var info = await dao.SaveInfo(ctx.request.body);
            ctx.body = {
                code: '002',
                info: info
            }

        } catch (e) {
            console.log(e);
        }
    },
    DeleteInfo: async ctx => {
    	try {
	    await dao.DeleteInfo();
	    ctx.body = {
		    code:'003',
	    }
	} catch (e) {
	    console.log(e);
	}
    }

}
