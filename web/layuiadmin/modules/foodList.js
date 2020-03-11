layui.define(function (exports) {
    var num = 0;    //修改操作的次数
    layui.use(['setter', 'table', 'form'], function () {
        var setter = layui.setter,
            table = layui.table,
            form = layui.form;
        form.render();

        var userId = 0;

        //获取父窗口用户名(等待cookie功能的开发)
        var userName = $('#userName', parent.document).text();
        //根据用户名查询用户id
        $.ajax({
            type: "get",
            url: setter.address + "user/getUserId",
            data: {
                'userName': userName
            },
            success: function(data){
                userId = data;
            }
        })

        //初始化并读取表格信息
        initTable(table, setter);

        //获取后台所有的菜品类型
        getAllFoodTypes(setter, form);

        //获取后台购物车记录数量
        getShoppingCarCount(setter);

        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            //删除按钮操作
            if (obj.event === 'del') {
                delMenuMsg(setter, data, obj);
            }
            //修改按钮操作
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
            //加入购物车按钮操作
            if (obj.event === 'add') {
                addShoppingCar(setter, data, userId);
            }
            
        });

        //搜索按钮点击事件(模糊查询)
        $('#select').on('click', function(){
            var foodName = $('#foodReload').val();
            table.reload('foodReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    foodName: foodName
                },
                method: 'get',
                url: 'http://localhost:8080/menu/getMenuLikeNameMsg'
            })
        })
    });
    exports('foodList', {})
});

//初始化并读取表格信息
function initTable(table, setter){
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
            console.log('菜单信息查询成功!');
        }
    })
}

//获取后台所有的菜品类型
function getAllFoodTypes(setter, form){
    $.ajax({
        url: setter.address + 'menu/getTypes',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            var list = data; //返回的数据
            var server = document.getElementById("selectType"); //server为select定义的id
            for (var p in list) {
                var option = document.createElement("option"); // 创建添加option属性
                option.setAttribute("value", p); // 给option的value添加值
                option.innerText = list[p]; // 打印option对应的纯文本
                server.appendChild(option); // 给select添加option子标签
                form.render("select"); // 刷新select，显示出数据
            }
        }, error: function(e) {
            layer.msg('错误'+e.readyState);
        }

    })
}

//获取后台购物车记录数量
function getShoppingCarCount(setter){
    $.ajax({
        type: "GET",
        url: setter.address + "shoppingCar/getShoppingCarCount",
        success: function (data) {
            console.log("数据库购物车记录条数:" + data);
            if (data > 99) {
                $('#carNum').text("99+");
            } else {
                $('#carNum').text(data);
            }
        }, error: function(e) {
            layer.msg('错误'+e.readyState);
        }
    })
}

//修改按钮弹窗功能
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
                        layer.msg('修改成功!', {icon:1,time:1000},function(){
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

//加入购物车功能
function addShoppingCar(setter, data, userId){
    $.ajax({
        type: "POST",
        url: setter.address + "shoppingCar/insertShoppingCar",
        data: {
            'carUserId': userId,
            'carFoodId': data.foodId,
            'carFoodName': data.foodName,
            'carOnePrice': data.foodPrice
        },
        success: function(data){
            if(data == 1){
                layer.msg('已加入购物车', {icon:1,time:1000});
                //服务端获取购物车物品件数
                $.ajax({
                    type: "GET",
                    url: setter.address + "shoppingCar/getShoppingCarCount",
                    success: function(data){
                        console.log("数据库购物车记录条数:"+data);
                        if(data > 99){
                            $('#carNum').text("99+");
                        }else{
                            $('#carNum').text(data);
                        }
                    },
                    error: function (e) {
                        alert('失败'+e.readyState);
                    }
                })
            }else{
                layer.msg('加入购物车错误!', {icon:2,time:1000});
            }
        },
        error: function (e) {
            alert('失败'+e.readyState);
        }
    })
}

//删除单条记录按钮操作
function delMenuMsg(setter, data, obj){
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
                    layer.msg('删除成功!', {icon:1,time:1000},function(){
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
}

