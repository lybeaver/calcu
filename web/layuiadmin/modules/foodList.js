layui.define(function (exports) {
    var num = 0;
    layui.use(['setter', 'table','laypage'], function () {
        var setter = layui.setter,
            table = layui.table;
        //第一个实例
        table.render({
            id: 'foodReload'
            , elem: '#foodTable'
            , height: 472
            , url: setter.address + 'menu/page' //数据接口
            , page: true //开启分页
            , limits: [10,20,50]
            , even: true
            , skin: 'row'
            , cols: [[ //表头
                {type:'checkbox'}
                , { field: 'foodName', title: '名称', width: 200, sort: true }
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
            //删除按钮操作
            if (obj.event === 'del') {
                layer.confirm('真的要删除这道菜么?', function (index) {
                    $.ajax({
                        type: "POST",
                        url: setter.address + "menu/delFoodById",
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=utf-8",
                        success: function(data){
                            if(data == 1){
                                layer.closeAll('page');//关闭所有页面层
                                /* 触发弹层并刷新 */
                                layer.msg('删除成功!', {icon:1,time:500},function(){
                                    obj.del();
                                    layer.close(index);
                                });
                            }
                        },
                        error: function (e) {
                            alert('失败'+e.readyState);
                        }
                    })
                });
                //编辑
            }
            if (obj.event === 'edit') {
                num = num+1;
                if(num == 1){
                    $('#foodId').attr("value", data.foodId);
                    $('#foodName').attr("value", data.foodName);
                    $('#foodType').attr("value", data.foodType);
                    $('#foodPrice').attr("value", data.foodPrice);
                    $("#foodNum").attr("value", data.foodNum);
                }else{
                    $('#foodId').val(data.foodId);
                    $('#foodName').val(data.foodName);
                    $('#foodType').val(data.foodType);
                    $('#foodPrice').val(data.foodPrice);
                    $("#foodNum").val(data.foodNum);
                }
                updMenuMsg(obj, setter);
            }
            
        });
    });
    exports('foodList', {})
});

function updMenuMsg(obj, setter){
    layer.open({
        title: '修改信息'
        ,content: $('#aa')
        ,type: 1
        ,btn: ['提交', '取消']
        ,yes: function(){
            //按钮【按钮一】的回调
            //updMenuMsg(index);
            var foodId = $('#foodId').val();
            var foodName = $('#foodName').val();
            var foodType = $('#foodType').val();
            var foodPrice = $('#foodPrice').val();
            var foodNum = $("#foodNum").val();
            $.ajax({
                type: "POST",
                url: setter.address + "menu/updMenuMsg",
                data: {
                    'foodId': foodId,
                    'foodName': foodName,
                    'foodType': foodType,
                    'foodPrice': foodPrice,
                    'foodNum': foodNum
                },
                success: function(data){
                    if(data == 1){
                        layer.closeAll('page');//关闭所有页面层
                        /* 触发弹层并刷新 */
                        layer.msg('修改成功!', {icon:1,time:500},function(){
                            obj.update({
                                foodId: foodId,
                                foodName: foodName,
                                foodType: foodType,
                                foodPrice: foodPrice,
                                foodNum: foodNum
                            });
                        });
                    }
                },
                error: function (e) {
                    alert('失败'+e.readyState);
                }
            })
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

