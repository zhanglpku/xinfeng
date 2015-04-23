<%@ page language="java" import="java.util.*,com.pku.xinfeng.utils.Constant" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="${pageContext.request.contextPath}/js/datepicker/WdatePicker.js" defer="defer" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<LINK href="${pageContext.request.contextPath}/css/style.css" type=text/css rel=stylesheet>
<script type="text/javascript">
	function submitFun(){
		var operType = document.getElementById("operTypeID").value;
		var operDateBegin = document.getElementById("operDateBeginID").value;
		var operDateEnd = document.getElementById("operDateEndID").value;
		var operLevel = document.getElementById("operLevelID").value;
		
		if(operDateBegin!='' && operDateEnd!='') {
			if(operDateBegin > operDateEnd) {
				alert("结束时间要大于开始时间！");
				return false;
			}
	  	}
		document.getElementById("resultListDiv").innerHTML = "";
		$.post("<%=path%>/sys/getLogList",
			{operType:operType,operDateBegin: operDateBegin,operDateEnd:operDateEnd,operLevel: operLevel},
			function(data){
				if("" != data){
					var json = eval('(' + data + ')');
					var list = json.list;
					if(null != list){
						var len = list.length;
						if(len > 0){
							var inHtml = "";
							for(var j=0; j<len; j++){ 
								var obj = list[j];
								
								inHtml += "<tr>"
								inHtml += "<td width=\"40\" align=\"right\">" + (j+1) + "</td>";
								inHtml += "<td width=\"80\" align=\"center\">" + obj.operType + "</td>";	
								inHtml += "<td>" + obj.operData + "</td>";	
								inHtml += "<td width=\"140\">" + obj.operTime + "</td>";	
								inHtml += "<td width=\"80\">" + obj.operLevel + "</td>";	
								inHtml += "</tr>"
							}
							document.getElementById("resultListDiv").innerHTML = inHtml;
						}
					}
				}
		 }); 
		
	}
</script>
</head>
<body>
	<div style="position: absolute; top:0px;left:0px;width:100%;height:30px;background: #00FFc2;">
	<!-- 查询条件 -->
		<form name="actionForm" method="post" action="<%=path%>/getLogList" >
			<table>
				<tr>
					<td>
						操作类型：
						<select name="operType" id="operTypeID" style="width: 180px;">
							<option value="">-请选择-</option>
							<option value="<%=Constant.OPER_TYPE_ADD %>">增加</option>
							<option value="<%=Constant.OPER_TYPE_DELETE%>">删除</option>
							<option value="<%=Constant.OPER_TYPE_MODIFY%>">修改</option>
						</select>
					</td>
					<td>
						操作时间：
						<input type="text" name="operDateBegin" id="operDateBeginID" 
							onclick="WdatePicker();" style="width:100px;" readonly/>
							&nbsp;
						<img onclick="WdatePicker({el:'operDateBeginID'})" src="<%=path %>/js/datepicker/datePicker.gif" width="16" height="28" align="middle" />
						至
						<input type="text" name="operDateEnd"  id="operDateEndID" 
							onclick="WdatePicker();" style="width:100px;" readonly/>
							&nbsp;
						<img onclick="WdatePicker({el:'operDateEndID'})" src="<%=path %>/js/datepicker/datePicker.gif" width="16" height="28" align="middle" />
					
					</td>
					<td>
						操作级别：
						<select name="operLevel" id="operLevelID" style="width: 180px;">
							<option value="">-请选择-</option>
							<option value="<%=Constant.OPER_TYPE_SYS%>">系统管理员</option>
							<option value="<%=Constant.OPER_TYPE_COMMON%>">APP用户</option>
							<option value="<%=Constant.OPER_TYPE_AUTO%>">接口</option>
						</select>
					</td>
					
					<td>
						<input type="button" value="查询" onclick="submitFun();">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="position: absolute; top:30px;left:0px;width:100%;">
	<!-- 列表  表头-->
		<table class="tableList">
			<tr>
				<th width="40">序号</th>
				<th width="80">操作类型</th>
				<th>操作内容</th>
				<th width="140">操作时间</th>
				<th width="80">操作级别</th>
			</tr>
		</table>
	</div>
	<div style="position: absolute; top:65px;left:0px;width:100%;">
	<!-- 列表 内容 -->
		<table id="resultListDiv" class="tableList">
						 
		</table>
	</div>
</body>
</html>