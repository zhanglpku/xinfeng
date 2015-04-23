<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
		document.onkeydown = function(evt){
			var evt = window.event?window.event:evt;
			if(evt.keyCode==13){
				submitFun();
			}
		}
		function submitFun(){
		   var username = document.getElementById("username").value;
		   var password = document.getElementById("password").value;
		   if(trim(username) == "" || trim(password) == ""){
		       alert("请输入用户名或密码！");
		       return;
		   	}else{
		   		document.actionForm.action = "<%=path%>/sys/login";
				document.actionForm.target = "_self";
				document.actionForm.submit();
		   	}
		}
	</script>
</head>
<body>
	<div style="position:absolute;top:50%;left:50%;margin:-100px 0 0 -100px;width:300px;height:250px;background-color:#057FCC;">
		<form name="loginForm" action="<%=path%>/sys/login" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr height="50px;">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr height="50px;">
					<td align="right">用户名：</td>
					<td><input type="text" id="username" name="username" style="width: 200px;" /></td>
				</tr>
				<tr height="50px;">
					<td align="right">密码：</td>
					<td><input type="text" id="password" name="password" style="width: 200px;" ></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button onclick="submitFun();">登录</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>