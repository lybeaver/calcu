//layui.define(function (exports) {
    layui.use(['form', 'jquery'], function () {
        var $$ = layui.jquery;
        var form = layui.form()

        // 各种基于事件的操作，下面会有进一步介绍
        form.on('switch(test)', function(data) {
            console.log(data.elem); // 得到checkbox原始DOM对象
            console.log(data.elem.checked); // 开关是否开启，true或者false
            console.log(data.value); // 开关value值，也可以通过data.elem.value得到
            console.log(data.othis); // 得到美化后的DOM对象

            // 通过取得同级的其他元素,拿到相应的，其他的元素
            var kglx = $$(this).siblings().attr("id");// 得到开关类型的标签
            var h_id=$$(this).siblings().val();// 得到这一行的ID
            alert("开关类型标签:"+kglx+";行ID:"+h_id);
            var boolean1=data.elem.checked;
            alert("返回true还是false=" +boolean1);
            // 这样我就知道传进来的是谁了,然后再通过ajax来做

            $.ajax({
                type:"post",
                data:{
                    'userName':$('#userName').val()
                },
                url:setInterval.address + 'user/checkUserName',
                error:function(XMLHttpRequest,textStatus,errorThrown){
                    alert("执行失败了!");
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert("textStatus");
                    
                },
                success:function(data){
                    alert("成功啦？");
                }
                
        });
        return false;// 阻止表单跳转。如果需要表单跳转,去掉这段即可。

        });
exports('users', {})
//});