layui.define(function (exports) {
    layui.use(['setter', 'table','laypage'], function () {
        var setter = layui.setter,
            laypage = layui.page,
            table = layui.table;
        //第一个实例
        table.render({
            id: 'foodReload'
            , elem: '#foodTable'
            , height: 472
            , url: setter.address + 'menu/page' //数据接口
            , page: true //开启分页
            , limits: [10,20,50]
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
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    var json = eval('(' + JSON.stringify(data) + ')');//String转json
                    console.log(json['storeId']);
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
            }
            if (obj.event === 'edit') {
                var json = eval('(' + JSON.stringify(data) + ')');//String转json
                console.log(data);
                $('#foodId').attr("value", data.foodId);
                $('#foodName').attr("value", data.foodName);
                $('#foodType').attr("value", data.foodType);
                $('#foodPrice').attr("value", data.foodPrice);
                $("#foodNum").attr("value", data.foodNum);
                layer.open({
                    title: '修改信息'
                    ,content: $('#aa')
                    ,type: 1
                    ,btn: ['提交', '取消']
                    ,yes: function(index, layero){
                        //按钮【按钮一】的回调
                        updMenuMsg();
                    }
                    ,btn2: function(index, layero){
                        //按钮【按钮二】的回调
                        return true;
                        //return false //开启该代码可禁止点击该按钮关闭
                    }
                    ,shade: [0.3, '#000']
                    ,shadeClose: true
                    ,resize: false
                    ,area: ['400px', '342px']
                })
            }
            
        });
    });
    exports('foodList', {})
});

function updMenuMsg(){
    var foodId = $('#foodId').val();
    var foodName = $('#foodName').val();
    var foodType = $('#foodType').val();
    var foodPrice = $('#foodPrice').val();
    var foodNum = $("#foodNum").val();
    console.log(foodPrice);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/menu/updMenuMsg",
        data: {
            'foodId': foodId,
            'foodName': foodName,
            'foodType': foodType,
            'foodPrice': foodPrice,
            'foodNum': foodNum
        },
        dataType: "text",	/*后端返回的数据格式*/
        success: function(data){
            if(data == 1){
                layer.closeAll('page');//关闭所有页面层
                /* 触发弹层 */
                layer.msg('修改成功!');
                
            }
        },
        error: function (e) {
            alert('失败'+e.readyState);
        }
    })
}

