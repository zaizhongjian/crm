layui.use([ 'form', 'layer'], function() {
	var form = layui.form;
	var formSelects = layui.formSelects;
	layer = parent.layer === undefined ? layui.layer : top.layer,
	$ = layui.jquery;
	form.on("submit(addUser)", function(data) {
		if($("#password1").val()!==$("#password2").val()){
			layer.msg("两次密码不正确!!!");
			return false;
		}
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		// 实际使用时的提交信息
	
		$.post("/user/insertOrUpdate", {
			userId : $("#userId").val(),
			username : $(".userName").val(), //登录名
			roleIds:formSelects.value('roleSelect', 'val'),
//			password1:$("#password1").val(),
//			password2:$("#password2").val(),
			email : $(".userEmail").val(), //邮箱
			mobile : $("#userPhone").val(), //邮箱
			sex : data.field.sex, //性别
			status : data.field.userStatus, //用户状态
			newsTime : submitTime, //创建
		}, function(res) {
			
		})
		setTimeout(function() {
			top.layer.close(index);
			top.layer.msg("用户添加成功！");
			layer.closeAll("iframe");
			//刷新父页面
			parent.location.reload();
		}, 2000);
		return false;
	})

	//格式化时间
	function filterTime(val) {
		if (val < 10) {
			return "0" + val;
		} else {
			return val;
		}
	}
	//定时发布
	var time = new Date();
	var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());

})