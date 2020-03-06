layui.define(function (exports) {
    layui.use(['setter', 'table', 'laypage', 'laytpl'], function () {
        var setter = layui.setter,
            laypage = layui.page,
            table = layui.table;
        //第一个实例
        //第二种 不用url
        table.render({
            id: 'userReload',
            elem: '#userTable',
            height: 472,
            url: setter.address + 'user/page' //数据接口
                ,
            page: true //开启分页
                ,
            limits: [3, 5, 7],
            cols: [
                [ //表头
                    {
                        field: 'userName',
                        title: '名称',
                        width: 200,
                        sort: true
                    }, {
                        field: 'userType',
                        title: '类型',
                        width: 260,
                        sort: true,
                        templet: function (d) {
                            if (d.userType == '0') return '管理员';
                            else if (d.userType == '1') return '商家';
                            else if (d.userType == '2') return '用户';
                        }
                    }, {
                        field: 'logTime',
                        title: '注册/修改时间',
                        width: 320,
                        type: 'date',
                        templet: "<div>{{layui.util.toDateString(d.logTime, 'yyyy-MM-dd HH:mm:ss')}}</div> "
                    }, {
                        fixed: 'right',
                        title: '操作',
                        width: 310,
                        align: 'center',
                        toolbar: '#toolBar'
                    }, {
                        field: 'userId',
                        title: 'ID',
                        hide: true
                    }
                ]
            ],
            done: function (res, curr, count) {
                //console.log(res);
            }
        });


        //执行一个laypage实例
        // laypage.render({
        //     elem: 'userTable' //注意，这里的 test1 是 ID，不用加 # 号
        //     ,count: 50 //数据总数，从服务端得到
        // })
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    var json = eval('(' + JSON.stringify(data) + ')'); //String转json
                    log(json['storeId'])
                    $.post("路径", {
                        "参数": null
                    }, function (msg) {
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
                var json = eval('(' + JSON.stringify(data) + ')'); //String转json
                layerOpen('路径' + '参数', '编辑店铺信息', '670px;', '530px;');
            }
        });
    });
    exports('users', {})
});