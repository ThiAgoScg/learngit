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
<title>添加用户</title>
<script type="text/javascript">
	function sysusersave(){
		$.ajax({
			type : "post",
			url : '${baseurl}user/submitUser.action',
			data : $('#userform').serialize(),
			success:function(data){
				var id = data.sysdata.id;
				if(data.type=='0'){
					$('#sysuser_useridTip').html('');
					$('#sysuser_usernameTip').html('');
					$('#sysuser_passwordTip').html('');
					$('#sysuser_groupidTip').html('');
					$('#sysuser_sysmcTip').html('');
					$('#sysuser_userstateTip').html('');
					$('#sysuser_addrTip').html('');
					$('#sysuser_phoneTip').html('');
					$('#sysuser_contactTip').html('');
					$('#sysuser_emailTip').html('');
					$('#'+id+'Tip').html(data.message);
				}else if(data.type=='1'){
					$('#sysuser_useridTip').html('');
					$('#sysuser_usernameTip').html('');
					$('#sysuser_passwordTip').html('');
					$('#sysuser_groupidTip').html('');
					$('#sysuser_sysmcTip').html('');
					$('#sysuser_userstateTip').html('');
					$('#sysuser_addrTip').html('');
					$('#sysuser_phoneTip').html('');
					$('#sysuser_contactTip').html('');
					$('#sysuser_emailTip').html('');
					$('#'+id+'Tip').html(data.message);
				}
			}
		});
	}
</script>
</head>
<body>


<form id="userform" action="#" method="post">
<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>
   <TBODY>
   <TR>
		<TD background=images/r_0.gif width="100%">
			<TABLE cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR>
						<TD>&nbsp;系统用户信息</TD>
						<TD align=right>&nbsp;</TD>
					</TR>
				</TBODY>
			</TABLE>
		</TD>
	</TR>
	
	<TR>
		<TD>
			<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
				align=center>
				<TBODY>
					
					<TR>
						<TD height=30 width="15%" align=right >用户账号：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入账号" type="text" id="sysuser_userid" name="sysuserCustom.userid"  />
							</div>
						<!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
							<div id="sysuser_useridTip" style="color: #f00"></div>
						</TD>
						
						<TD height=30 width="15%" align=right >用户名称：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入用户名" type="text" id="sysuser_username" name="sysuserCustom.username"  />
							</div>
							<div id="sysuser_usernameTip" style="color: #f00"></div>
						</TD>
					</TR>
					<TR>
						<TD height=30 width="15%" align=right >用户密码：</TD>
						<TD class=category width="35%">
						<div>
							<input placeholder="请输入密码" type="password" id="sysuser_password" name="sysuserCustom.pwd" />
						</div>
						<div id="sysuser_passwordTip" style="color: #f00"></div>
						</TD>
						<TD height=30 width="15%" align=right >用户类型：</TD>
						<TD class=category width="35%">
						<div>
						<select name="sysuserCustom.groupid" id="sysuser_groupid">
							<option value="">请选择</option>
							<c:forEach items="${dictinfos }" var="list">
							<option value="${list.dictcode }">${list.info }</option>
							</c:forEach>
						</select>
						</div>
						<div id="sysuser_groupidTip" style="color: #f00"></div>
						</TD>
						
						
					</TR>
					<TR>
					    <TD height=30 width="15%" align=right >用户单位名称：</TD><!-- 用处：根据名称获取单位id -->
						<TD class=category width="35%">
						<div><input placeholder="请输入单位名称"  type="text" name="sysuserCustom.sysmc" /></div>
						<div id="sysuser_sysmcTip" style="color: #f00"></div>
						</TD>
						<TD height=30 width="15%" align=right>用户状态：</TD>
						<TD class=category width="35%">
						<div>
							<input type="radio" name="sysuserCustom.userstate" value="1" />正常
							<input type="radio" name="sysuserCustom.userstate" value="0" />暂停
						</div>
						<div id="sysuser_userstateTip" style="color: #f00"></div>
						</TD>
					</TR>
					<TR>
						<td height=30 width="15%" align=right >地址:</td>
						<td class=category width="35%">
						<div><input placeholder="请输入地址" type="text" name="sysuserCustom.addr" /></div>
						<div id="sysuser_addrTip" style="color:#f00"></div>
						</td>
						<td height=30 width="15%" align=right>电话:</td>
						<td>
							<div><input placeholder="请输入电话" maxlength="11" type="text" name="sysuserCustom.phone" /></div>
							<div id="sysuser_phoneTip" style="color:#f00"></div>
						</td>
					</TR>
					<TR>
						<td height=30 width="15%" align=right >联系方式:</td>
						<td class=category width="35%">
						<div><input placeholder="请输入联系方式" type="text" name="sysuserCustom.contact" /></div>
						<div id="sysuser_contactTip" style="color:#f00"></div>
						</td>
						<td height=30 width="15%" align=right>email邮箱:</td>
						<td>
							<div><input placeholder="请输入email邮箱" type="text" name="sysuserCustom.email" /></div>
							<div id="sysuser_emailTip" style="color:#f00"></div>
						</td>
					</TR>
					<tr>
						<td><div id="sysuser_msgTip" style="color: #f00"></div></td>
					</tr>
					<tr>
					  <td colspan=4 align=center class=category>
						<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="sysusersave()">提交</a>
						<a id="closebtn"  class="easyui-linkbutton" iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">关闭</a>
					  </td>
					</tr>
				
					</TBODY>
				</TABLE>
			</TD>
		</TR>
   </TBODY>
</TABLE>
</form>
</body>
</html>