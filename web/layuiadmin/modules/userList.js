layui.define(function (exports) {
    layui.use(['setter', 'table', 'laypage', 'laytpl'], function () {
        var setter = layui.setter,
            laypage = layui.page,
            table = layui.table;

        //初始化并读取表格信息
        initTable(table, setter);

        //执行一个laypage实例
        // laypage.render({
        //     elem: 'userTable' //注意，这里的 test1 是 ID，不用加 # 号
        //     ,count: 50 //数据总数，从服务端得到
        // })
        var num = 0;
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            //删除按钮操作
            if (obj.event === 'del') {
                delUserMsg(setter, data, obj);
            }
            //修改按钮操作
            if (obj.event === 'edit') {
                num = num + 1;
                if (num == 1) {
                    $('#userId').attr("value", data.userId);
                    $('#userName').attr("value", data.userName);
                    $('#userType').attr("value", data.userType);
                } else {
                    $('#userId').attr("value", data.userId);
                    $('#userName').attr("value", data.userName);
                    $('#userType').attr("value", data.userType);
                }
                updUserMsg(obj, setter);
            }

        });
    });
    exports('userList', {})
});

//第一个实例
function initTable(table, setter) {
    table.render({
        id: 'userReload',
        elem: '#userTable',
        height: 472,
        url: setter.address + 'user/page' //数据接口
            ,
        // skin: 'row'
        // ,
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
    })
};

//更新按钮弹窗功能
function updUserMsg(obj, setter) {
    layer.open({
        title: '修改信息',
        content: $('#uu'),
        type: 1,
        btn: ['提交', '取消'],
        yes: function () {
            //按钮【按钮一】的回调
            //updMenuMsg(index);
            var userId = $('#userId').val();
            var userName = $('#userName').val();
            var userType = $('#userType').val();
            $.ajax({
                type: "POST",
                url: setter.address + "menu/updMenuMsg",
                data: {
                    'userId': userId,
                    'userName': userName,
                    'userType': userType,
                },
                success: function (data) {
                    if (data == 1) {
                        layer.closeAll('page'); //关闭所有页面层
                        /* 触发弹层并刷新 */
                        layer.msg('修改成功!', {
                            icon: 1,
                            time: 1000
                        }, function () {
                            obj.update({
                                userId: userId,
                                userName: userName,
                                userType: userType,
                            });
                        });
                    }
                },
                error: function (e) {
                    alert('失败' + e.readyState);
                }
            })
        },
        btn2: function (index, layero) {
            //按钮【按钮二】的回调
            return true;
            //return false //开启该代码可禁止点击该按钮关闭
        },
        shade: [0.3, '#000'],
        shadeClose: true,
        resize: false,
        area: ['400px', '342px']
    })
}