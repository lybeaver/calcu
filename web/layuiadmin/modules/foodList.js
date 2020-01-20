layui.define(function (exports) {


    layui.use(['setter', 'table'], function () {
        var setter = layui.setter,
            table = layui.table;

        //第一个实例
        table.render({
            elem: '#foodTable'
            , height: 500
            , url: setter.address + 'selectAll' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                { field: 'foodName', title: '名称', width: 230, sort: true }
                , { field: 'foodType', title: '类型', width: 230, sort: true }
                , { field: 'foodPrice', title: '价格', width: 150, sort: true }
                , { field: 'foodNum', title: '剩余数量', width: 120, sort: true }
                , { field: 'foodId', title: 'ID', hide: true }
            ]], done: function (res, curr, count) {

                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }
        });

    });

    exports('foodList', {})
});