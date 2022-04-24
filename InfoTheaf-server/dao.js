const Info = require('./model')

module.exports = {
    GetInfo: async ()=> {
        var infos = await Info.find();
        return infos;
    },

    SaveInfo: async (infoObject) => {
        var id = Math.floor(Math.random() * 999999999);
        var info = new Info({
            _id: id,
            info: infoObject
        });
        await info.save()
    },
    DeleteInfo: async () => {
	await Info.deleteMany({});    
}
}
