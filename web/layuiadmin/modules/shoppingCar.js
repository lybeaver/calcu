layui.define(function (exports) {
    layui.use(['table', 'form'], function () {
        var table = layui.table,
            form = layui.form;
        form.render();
    
        table.render({
            id: 'carId',
            elem: '#shoppingTable',
            url: 'http://localhost:8080/shoppingCar/getCarMsg', //数据接口
            even: true,
            skin: 'row',
            cols: [[
                //表头
                { type: 'checkbox'}, 
                { field: 'carFoodName', title: '名称', width: 200, sort: true }, 
                { field: 'carUserId', title: '用户id', hide: true }, 
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
        table.on('edit(shoppingTable)', function(obj){
            alert(obj.value);
        })
    })
})
