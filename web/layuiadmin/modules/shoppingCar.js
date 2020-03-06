layui.define(function (exports) {
    layui.use(['table', 'form', 'setter'], function () {
        var table = layui.table,
            form = layui.form,
            setter = layui.setter;
        form.render();
    
        //初始化表格
        table.render({
            id: 'carReload',
            elem: '#shoppingTable',
            url: setter.address + 'shoppingCar/getCarMsg', //数据接口
            even: true,
            skin: 'row',
            cols: [[
                //表头
                { type: 'checkbox'}, 
                { field: 'carFoodName', title: '名称', width: 200, sort: true }, 
                { field: 'carUserId', title: '用户ID', hide: true }, 
                { field: 'carOnePrice', title: '单价', width: 150, sort: true }, 
                { field: 'carFoodNum', title: '数量', width: 150, sort: true, edit: 'text' }, 
                { field: 'carAllPrice', title: '金额', width: 150, sort: true }, 
                { fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#toolBar' }, 
                { field: 'carId', title: 'ID', hide: true }
            ]], done: function (res, curr, count) {
                console.log('购物车信息查询成功!');
            }
        })

        //监听单元格编辑
        table.on('edit(car)', function (obj) {
            var value = obj.value,  //得到修改后的值
                data = obj.data,    //得到所在行所有键值
                field = obj.field;  //得到字段
            
            //将修改的值传入后台
            $.ajax({
                type: "POST",
                url: setter.address + "shoppingCar/updFoodNum",
                data: {
                    'carId': data.carId,
                    'carFoodNum': value,
                    'carAllPrice': data.carOnePrice * value
                },
                success: function(result){
                    if(result == 1){
                        //同步修改该记录的总价
                        obj.update({
                            carAllPrice: data.carOnePrice * value
                        })
                    }else{
                        layer.msg('失败啦');
                    }
                },
                error: function (e) {
                    alert('失败'+e.readyState);
                }
            })
        })

        //监听工具条
        table.on('tool(car)', function(obj){
            var data = obj.data;
            console.log('删除的记录id:'+data.carId);
            //删除按钮操作
            if(obj.event === 'del') {
                $.ajax({
                    type: "POST",
                    url: setter.address + "shoppingCar/delShoppingMsg",
                    data: {
                        'carId': data.carId
                    },
                    success: function (result) {
                        if(result == 1){
                            layer.msg('删除成功!', {icon:1, time:1000});
                        }else{
                            layer.msg('删除失败!', {icon:5, anim: 6});
                        }
                    },
                    error: function (e) {
                        alert('失败'+e.readyState);
                    }
                })
            }
        })

    })
})
