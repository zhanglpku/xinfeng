<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" import="java.util.*,com.pku.xinfeng.utils.Constant" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	<script src="${pageContext.request.contextPath}/js/datepicker/WdatePicker.js" defer="defer" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
	<LINK href="${pageContext.request.contextPath}/css/style.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
		function exportFun(url){
			if(null == url || "" == url)
				return;
			document.actionForm.action = "<%=path%>/sys/" + url;
			document.actionForm.target = "_self";
			document.actionForm.submit();
		}
	</script>
</head>
<body>
<form name="actionForm" method="post">
	<table style="position: absolute; top:10px;left: 20px;text-align:right;">
		<tr>
			<td>用户名、电话、设备ID、维修时间、滤网更换时间</td>
			<td><button onclick="exportFun('exportUserDetailList');">导出</button></td>
		</tr>
		<tr>
			<td>设备ID、传感器数据</td>
			<td><button onclick="exportFun('exportEquipSensorDatas');">导出</button></td>
		</tr>
	</table>
</form>
</body>
</html>