layui.use([ 'form', 'layer', 'table', 'laytpl' ], function() {
	var form = layui.form;
	layer = parent.layer === undefined ? layui.layer : top.layer,
	$ = layui.jquery,
	laytpl = layui.laytpl,
	table = layui.table;

	//用户列表
	var tableIns = table.render({
		elem : '#userList',
		url : '/user/list',
		cellMinWidth : 95,
		page : true,
		height : "full-125",
		limits : [ 10, 15, 20, 25 ],
		limit : 10,
		id : "userListTable",
		cols : [ [
			{
				type : "checkbox",
				fixed : "left",
				width : 50
			},
			{
				field : 'userId',
				title : '用户id',
				width : 70,
				align : "center"
			},
			{
				field : 'username',
				title : '用户名',
				minWidth : 100,
				align : "center"
			},
			{
				field : 'email',
				title : '用户邮箱',
				minWidth : 200,
				align : 'center',
				templet : function(d) {
					return '<a class="layui-blue" href="mailto:' + d.email + '">' + d.email + '</a>';
				}
			},
			{
				field : 'mobile',
				title : '手机号码',
				minWidth : 100,
				align : "center"
			},
			{
				field : 'status',
				title : '用户状态',
				align : 'center',
				templet : function(d) {
					return d.status == "1" ? "<span style='color:green'>正常使用<span>" : "<span style='color:red'>限制使用<span>";
				}
			},
			{
				field : 'status',
				title : '性别',
				align : 'center',
				maxWidth : 70,
				templet : function(d) {
					return d.status == "1" ? "男" : "女";
				}
			},
			{
				field : 'roleIds',
				title : '角色id',
				align : 'center',
				maxWidth : 70,
			},
			/*  {field: 'userGrade', title: '用户等级', align:'center',templet:function(d){
			      if(d.userGrade == "0"){
			          return "注册会员";
			      }else if(d.userGrade == "1"){
			          return "中级会员";
			      }else if(d.userGrade == "2"){
			          return "高级会员";
			      }else if(d.userGrade == "3"){
			          return "钻石会员";
			      }else if(d.userGrade == "4"){
			          return "超级会员";
			      }
			  }},*/
			{
				field : 'createTime',
				title : '创建时间',
				align : 'center',
				minWidth : 160
			},
			{
				title : '操作',
				minWidth : 175,
				templet : '#userListBar',
				fixed : "right",
				align : "center"
			}
		] ],
		done : function() {
			$("[data-field='roleIds']").css('display', 'none');
		}
	});

	//搜索【此功能需要后台配合，所以暂时没有动态效果演示】
	$(".search_btn").on("click", function() {
		//如果关键字为空，那就查询出所有数据
		table.reload("userListTable", {
			page : {
				curr : 1 //重新从第 1 页开始
			},
			where : {
				key : $(".searchVal").val() //搜索的关键字
			}
		})
	});

	//添加用户
	function addUser(edit) {
		var index = layui.layer.open({
			title : "添加用户",
			type : 2,
			content : "/index/toUserAdd",
			success : function(layero, index) {
				var formSelects = layui.formSelects;
				var body = layui.layer.getChildFrame('body', index);
				if (edit) {
					body.find(".userName").val(edit.username); //登录名
					//body.find("#password1").val(edit.password);  //登录名
					//body.find("#password2").val(edit.password);  //登录名
					body.find(".userEmail").val(edit.email); //邮箱
					body.find("#userPhone").val(edit.mobile); //邮箱
					body.find(".userSex input[value=" + edit.sex + "]").prop("checked", "checked"); //性别
					body.find(".userStatus").val(edit.status); //用户状态
					body.find("#userId").val(edit.userId);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
						tips : 3
					});
				}, 500)
			}
		})
		layui.layer.full(index);
		window.sessionStorage.setItem("index", index);
		//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
		$(window).on("resize", function() {
			layui.layer.full(window.sessionStorage.getItem("index"));
		})
	}
	$(".addNews_btn").click(function() {
		addUser();
	})

	//批量删除
	$(".delAll_btn").click(function() {
		var checkStatus = table.checkStatus('userListTable'),
			data = checkStatus.data,
			userId = [];
		if (data.length > 0) {
			for (var i in data) {
				userId.push(data[i].userId);
			}
			layer.confirm('确定删除选中的用户？', {
				icon : 3,
				title : '提示信息'
			}, function(index) {
				$.post("/user/deleteAll", {
					"userIds" : userId,
				}, function(data) {
					if (data.code == 0) {
						layer.alert("删除成功");
						tableIns.reload();
						layer.close(index);
					} else {
						layer.alert("删除成功");
						layer.close(index);
					}
				})
			})
		} else {
			layer.msg("请选择需要删除的用户");
		}
	})

	//列表操作
	table.on('tool(userList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;

		if (layEvent === 'edit') { //编辑
			addUser(data);
		} else if (layEvent === 'usable') { //启用禁用
			var _this = $(this),
				usableText = "是否确定禁用此用户？",
				btnText = "已禁用";
			if (_this.text() == "已禁用") {
				usableText = "是否确定启用此用户？",
				btnText = "已启用";
			}
			layer.confirm(usableText, {
				icon : 3,
				title : '系统提示',
				cancel : function(index) {
					layer.close(index);
				}
			}, function(index) {
				_this.text(btnText);
				layer.close(index);
			}, function(index) {
				layer.close(index);
			});
		} else if (layEvent === 'del') { //删除
			layer.confirm('确定删除此用户？', {
				icon : 3,
				title : '提示信息'
			}, function(index) {
				$.get("/user/delete", {
					userId : data.userId //将需要删除的newsId作为参数传入
				}, function(data) {
					if (data.code == 0) {
						layer.alert("删除成功");
						tableIns.reload();
						layer.close(index);
					} else {
						layer.alert("删除成功");
						layer.close(index);
					}
				})
			});
		}
	});

})