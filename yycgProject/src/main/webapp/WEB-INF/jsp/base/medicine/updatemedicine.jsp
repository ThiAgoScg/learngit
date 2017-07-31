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
	function medicinesave(){
		$.ajax({
			type : "post",
			url : '${baseurl}medicine/updateMedicine.action',
			data : $('#userform').serialize(),
			success:function(data){
				console.log(data);
				var id = data.sysdata.id;
					$('#ypxxCustom_mcTip').html('');
					$('#ypxxCustom_jxTip').html('');
					$('#ypxxCustom_ggTip').html('');
					$('#ypxxCustom_zhxsTip').html('');
					$('#ypxxCustom_bmTip').html('');
					$('#ypxxCustom_scqymcTip').html('');
					$('#ypxxCustom_spmcTip').html('');
					$('#ypxxCustom_zbjgTip').html('');
					$('#ypxxCustom_zlccTip').html('');
					$('#ypxxCustom_jyztTip').html('');
					$('#ypxxCustom_lbTip').html('');
					$('#ypxxCustom_msgTip').html('');
					$('#'+id+'Tip').html(data.message);
			}
		});
	}
</script>
</head>
<body>


<form id="userform" action="#" method="post">
<input type="hidden" name="ypxxCustom.id" value="${ypxx.id }">
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
						<TD height=30 width="15%" align=right >通用名：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入通用名" type="text" id="ypxxCustom_mc" name="ypxxCustom.mc" value="${ypxx.mc }" />
							</div>
						<!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
							<div id="ypxxCustom_mcTip" style="color: #f00"></div>
						</TD>
						
						<TD height=30 width="15%" align=right >剂型：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入剂型" type="text" id="ypxxCustom_jx" name="ypxxCustom.jx"  value="${ypxx.jx }"/>
							</div>
							<div id="ypxxCustom_jxTip" style="color: #f00"></div>
						</TD>
					</TR>
					<TR>
						<TD height=30 width="15%" align=right >规格：</TD>
						<TD class=category width="35%">
						<div>
							<input placeholder="请输入规格" type="text" id="ypxxCustom_gg" name="ypxxCustom.gg" value="${ypxx.gg }"/>
						</div>
						<div id="ypxxCustom_ggTip" style="color: #f00"></div>
						</TD>
						<TD height=30 width="15%" align=right >转换系数：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入转换系数" type="text" id="ypxxCustom_zhxs" name="ypxxCustom.zhxs"  value="${ypxx.zhxs }"/>
							</div>
							<div id="ypxxCustom_zhxsTip" style="color: #f00"></div>
						</TD>
						
					</TR>
					<TR>
						<TD height=30 width="15%" align=right >生产企业：</TD>
						<TD class=category width="35%">
							<div>
							<input placeholder="请输入生产企业" type="text" id="ypxxCustom_scqymc" name="ypxxCustom.scqymc" value="${ypxx.scqymc }" />
							</div>
							<div id="ypxxCustom_scqymcTip" style="color: #f00"></div>
						</TD>
						 <TD height=30 width="15%" align=right >商品名称：</TD><!-- 用处：根据名称获取单位id -->
						<TD class=category width="35%">
						<div><input placeholder="请输入商品名称"  type="text" name="ypxxCustom.spmc"  value="${ypxx.spmc }"/></div>
						<div id="ypxxCustom_spmcTip" style="color: #f00"></div>
						</TD>
					</TR>
					<TR>
						<TD height=30 width="15%" align=right>中标价格：</TD>
						<TD class=category width="35%">
						<div>
							<input placeholder="请输入中标价格"  style="width: 90px" type="text" name="ypxxCustom.zbjg" value="${ypxx.zbjg }" />
						</div>
						<div id="ypxxCustom_zbjgTip" style="color: #f00"></div>
						</TD>
						<TD height=30 width="15%" align=right >药品类别：</TD>
						<TD class=category width="35%">
						<div>
						<select name="ypxxCustom.lb" id="ypxxCustom_lb">
							<option  value="">请选择</option>
							<c:forEach items="${dictinfos }" var="list">
							<option value="${list.id }" ${ypxx.lb==list.id?"selected='selected'":"" }>${list.info }</option>
							</c:forEach>
						</select>
						</div>
						<div id="ypxxCustom_lbTip" style="color: #f00"></div>
						</TD>
					</TR>
					<TR>
						<TD height=30 width="15%" align=right >交易状态：</TD>
						<TD class=category width="35%">
						<div>
						<select name="ypxxCustom.jyzt" id="ypxxCustom_jyzt">
							<option value="">请选择</option>
							<c:forEach items="${dictinfos2 }" var="list">
							<option value="${list.dictcode }" ${list.dictcode==ypxx.jyzt?"selected='selected'":"" }>${list.info }</option>
							</c:forEach>
						</select>
						</div>
						<div id="ypxxCustom_jyztTip" style="color: #f00"></div>
						</TD>
						<TD height=30 width="15%" align=right >质量层次：</TD>
						<TD class=category width="35%">
						<div>
						<select name="ypxxCustom.zlcc" id="ypxxCustom_zlcc">
							<option value="">请选择</option>
							<c:forEach items="${dictinfos3 }" var="list">
							<option value="${list.id }"${list.id==ypxx.zlcc?"selected='selected'":"" }>${list.info }</option>
							</c:forEach>
						</select>
						</div>
						<div id="ypxxCustom_zlccTip" style="color: #f00"></div>
						</TD>
					</TR>
					<tr>
						<td></td><td><div id="ypxxCustom_msgTip" style="color: #f00"></div></td>
					</tr>
					<tr>
					  <td colspan=4 align=center class=category>
						<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="medicinesave()">提交</a>
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