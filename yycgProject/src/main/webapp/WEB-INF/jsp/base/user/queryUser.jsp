<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css" href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>
<title>查询用户</title>
<script type="text/javascript">
//加载数据datagrid
	$(function(){
		$('#sysuserlist').datagrid({
			title : '用户查询',//数据列表标题
			nowrap : true,//单元格中的数据不换行，true表示不换行，不换行情况下加载数据性能高，如果为false就是换行，换行数据加载性能不高。
			striped : true,//条文显示效果、
			url : '${baseurl}user/queryUser_result.action',//加载数据的连接，因连接请求过来的是json数据
			idField : 'id',//此字段很重要，数据结果集的唯一约束，如果写错影像，获取当前选中行的方法执行
			loadMsg : '',
			columns : columns_v,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			toolbar : toolbar_v
		});
	});
	
	//datagrid列的定义
	var columns_v = [[{
		field : 'userid',//对应的json中的key
		title : '账户',//对应的显示页面的标题
		width : 100
	},{
		field : 'username',
		title : '名称',
		width : 100,
	},{
		field : 'groupid',
		title : '名称',
		width : 100,
		/*  formatter : function(value,row,index){
			//通过此方法格式化显示内容，value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象，index表示序号
			 if(value == '1'){
				return "卫生局";
			}else if(value == '2'){
				return "卫生院";
			}else if(value == '3'){
				return "卫生室";
			}else if(value == '4'){
				return "供货商";
			}else if(value == '0'){
				return "系统管理员";
			} 
		}  */
	 },{
		field : 'groupname',
		title : '名称',
		width : 100,
	},{
		field : 'sysmc',
		title : '所属单元',
		width : 150
	},{
		field : 'userstate',
		title : '状态',
		width : 100,
		/* formatter : function(value,row,index){
			if(value=='1'){
				return "正常";
			}else if(value == '0'){
				return "暂停";
			}
		} */
	},{
		field : 'statename',
		title : '状态',
		width : 100,
	},{
		field : 'optration',
		title : '操作',
		width : 100,
		formatter : function(value,row,index){
			var id = row.id;
			var ids = id+"";
			return "<a href=javascript:removeuser('"+row.id+"')>删除</a>";
		}
	},{
		field : "upd",
		title : '更新',
		width : 100,
		formatter : function(value,row,index){
			return "<a href=javascript:updateuser('"+row.id+"')>修改</a>";
		}
	}]];
	//查询方法
	function queryuser(){
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#sysuserqueryForm").serializeJson();
		console.log(formdata);
		$('#sysuserlist').datagrid('load',{
			'sysuserCustom.userid':$('#userid').val(),
			'sysuserCustom.username':$('#username').val(),
			'sysuserCustom.sysmc':$('#sysmc').val(),
			'sysuserCustom.groupid':$('#groupid').val(),
			'sysuserCustom.userstate':$('#ustate').val()
		});
	}
	//删除用户
	function removeuser(id){
		$('#users').val(id);
		$.ajax({
			url:'${baseurl}user/removeUser.action',
			type:'POST',
			data:{userId:id},
			success:function(data){
				var msg = "您真的确定要删除吗？\n\n请确认！";
				if (confirm(msg)==true){
					if(data.type == 1){
						alert(data.message);
						queryuser();
					}else{
						alert(data.message);
					}
				return true;
				}else{
				return false;
				}
			}
		});
	}
	
	//更新用户弹框
	function updateuser(id){
		createmodalwindow("修改用户信息",800,350,'${baseurl}user/showupuser.action?userid='+id+'');
	}
	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnadd',
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			//打开一个窗口，用户添加页面
			//参数：窗口的title、宽、高、url地址
			createmodalwindow("添加用户信息", 800, 320, '${baseurl}user/addUser.action');
		}
	} ];
</script>
</head>
<body>
	<form id = "delete_id">
		<input id="users" type="hidden"  name = "userId" value="">
	</form>
	<!-- html的静态布局 -->
  <form id="sysuserqueryForm">
	<!-- 查询条件 -->
	<TABLE class="table_search">
		<TBODY>
			<TR>
				<TD class="left">用户账号：</td>
				<td><INPUT id = "userid" type="text" name="sysuserCustom.userid" value=""/></TD>
				<TD class="left">用户名称：</TD>
				<td><INPUT id = "username" type="text" name="sysuserCustom.username" value=""/></TD>

				<TD class="left">单位名称：</TD>
				<td><INPUT id = "sysmc" type="text" name="sysuserCustom.sysmc" value="" /></TD>
				<TD class="left">用户类型：</TD>   
				<td><select id = "groupid" name="sysuserCustom.groupid">
						<option value="">请选择</option>
						<c:forEach items="${dictinfos }" var="list">
							<option value="${list.dictcode }">${list.info }</option>
						</c:forEach>
				</select></TD>
				<TD class="left">用户状态</TD>
				<td>
					<select id = "ustate" name="sysuserCustom.userstate">
						<option value="">请选择</option>
						<c:forEach items="${state }" var="state">
							<option value="${state.dictcode }">${state.info }</option>
						</c:forEach>
					</select>
				</td>
				<td><a id="btn" href="#" onclick="queryuser()"
					class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
			</TR>


		</TBODY>
	</TABLE>

	<!-- 查询列表 -->
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" align="center">
		<TBODY>
			<TR>
				<TD>
					<table id="sysuserlist"></table>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>
</body>
</html>