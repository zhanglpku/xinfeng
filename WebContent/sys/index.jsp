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
		document.getElementById("resultListDiv").innerHTML = "";
		$.post("<%=path%>/sys/getUserEquipList",
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
								inHtml += "<td width=\"80\" align=\"center\">" + obj.userId + "</td>";	
								inHtml += "<td>" + obj.userName + "</td>";	
								inHtml += "<td width=\"110\">" + obj.phone + "</td>";	
								inHtml += "<td width=\"80\">" + obj.equipId + "</td>";	
								inHtml += "<td width=\"80\">" + obj.equipName + "</td>";	
								inHtml += "<td width=\"80\">" + obj.repairDate + "</td>";	
								inHtml += "<td width=\"80\">" + obj.filterDate + "</td>";	
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
	<div style="position: absolute; top:0px;left:0px;width:100%;height:100px;background: #0687c2;">
	  loglog
	</div>
	<div style="position: absolute; top:100px;left:0px;width:100%;height:30px;background: #00FFc2;">
	<!-- 查询条件 -->
		<form name="actionForm" method="post" >
			<input type="button" value="查询" onclick="submitFun();">
						<input type="reset" value="重置">
		</form>
	</div>
	<div style="position: absolute; top:130px;left:0px;width:100%;">
	<!-- 列表  表头-->
		<table class="tableList">
			<tr>
				<th width="40">序号</th>
				<th width="80">用户名</th>
				<th>电话</th>
				<th width="110">设备ID</th>
				<th width="80">设备名</th>
				<th width="80">设备维修时间</th>
				<th width="80">滤网更换时间</th>
			</tr>
		</table>
	</div>
	<div style="position: absolute; top:160px;left:0px;width:100%;">
	<!-- 列表 内容 -->
		<table id="resultListDiv" class="tableList">
						 
		</table>
	</div>
</body>
</html>