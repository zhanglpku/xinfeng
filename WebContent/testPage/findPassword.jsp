<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="http://127.0.0.1:8080/xinfeng/findPassword" method="post">
	userId：<input name="userId" type="text"></br>
	phoneNumber：<input type="text" name="phoneNumber" style="width: 100px;" /></br>
	verifyCode：<input name="verifyCode" type="text"></br>
	newPassword：<input name="newPassword" type="text"></br>
	<input type="submit">
</form>
</body>
</html>