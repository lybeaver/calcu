layui.define(function (exports) {
    layui.use(['setter', 'table','laypage'], function () {
        var setter = layui.setter,
            laypage = layui.page,
            table = layui.table;
        //第一个实例
        table.render({
            id: 'foodReload'
            , elem: '#foodTable'
            , height: 500
            , url: setter.address + 'menu/page' //数据接口
            , page: true //开启分页
            , limits: [10,20,30]
            , jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数
                
                //首次不执行
                if(!first){
                    //do something
                }
            }
            , cols: [[ //表头
                { field: 'foodName', title: '名称', width: 200, sort: true }
                , { field: 'foodType', title: '类型', width: 150, sort: true }
                , { field: 'foodPrice', title: '价格', width: 150, sort: true }
                , { field: 'foodNum', title: '剩余数量', width: 150, sort: true }
                , { fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#toolBar' }
                , { field: 'addShopping', title: '加入购物车', width: 150, align: 'center', toolbar: '#addTool' }
                , { field: 'foodId', title: 'ID', hide: true }
            ]], done: function (res, curr, count) {
                //console.log(res);
            }
        });
        //执行一个laypage实例
        laypage.render({
            elem: 'foodTable' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: 50 //数据总数，从服务端得到
        })
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    var json = eval('(' + JSON.stringify(data) + ')');//String转json
                    log(json['storeId'])
                    $.post("路径", { "参数": null }, function (msg) {
                        if (msg.code == 0) {
                            obj.del();
                            layer.close(index);
                        } else {
                            layer.msg('删除失败！');
                        }
                    })
                });
                //编辑
            } else if (obj.event === 'edit') {
                var json = eval('(' + JSON.stringify(data) + ')');//String转json
                layerOpen('路径' + '参数', '编辑店铺信息', '670px;', '530px;');
            }
        });
    });
    exports('foodList', {})
});