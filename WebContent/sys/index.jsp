<%@ page language="java"
	import="java.util.*,com.pku.xinfeng.utils.Constant"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	function setMainWidth() {
		document.getElementById("iframeID").style.height = $(window).height() - 100 + "px";
	}
</script>
</head>
<body onload="setMainWidth()">
	<div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 70px; background:#057FCC;">
		<p style="font-size:35px; text-align:center;">新风后台管理系统</p>
	</div>
	<div class="menu"
		style="position: absolute; top: 70px; left: 0px; width: 100%; height: 30px; background: #0687c2;">
		<ul>
			<li><a href="modifyPassword.jsp" target="mainIframe">修改密码</a></li>
			<li><a href="export.jsp" target="mainIframe">数据导出</a></li>
			<li><a href="equipVersion.jsp" target="mainIframe">设备型号</a></li>
			<li><a href="operList.jsp" target="mainIframe">日志查询</a></li>
			<li><a href="<%=path%>/sys/logout">退出</a></li>
		</ul>
	</div>
	<iframe id="iframeID" name="mainIframe" style="position: absolute; top: 100px; left: 0px; width: 100%;"
		frameborder="0"></iframe>
</body>
</html>
