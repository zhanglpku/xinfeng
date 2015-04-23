<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改密码</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
		function update(){
		   var oldpwd = document.getElementById("oldPassword").value;
		   var newpwd = document.getElementById("newpassword").value;
		   var repwd = document.getElementById("repwd").value;
		   if(trim(oldpwd) == ""){
		       alert("请输入旧的密码！");
		       return;
		   	}else if(trim(newpwd) == ""){
		   		alert("请输入新密码！");
		   		return;
		   	}else if(trim(repwd) == ""){
		   		alert("请输入确认密码!");
		   		return;
		   	}else if(trim(newpwd) != trim(repwd)){
		   		alert("新密码和确认密码输入的不一致请重新输入！");
		   		return;
		   	}
		   $.post("<%=path%>/sys/modifySysPassword",
					{oldPassword:oldpwd,newPassword:newpwd},
					function(data){
						if("" != data){
							var json = eval('(' + data + ')');
							var state = json.state;
							if(state){
								alert(json.description);
								$("input[type=reset]").trigger("click");
							}else{
								alert(json.description);
							}
						}
				 }); 
		}
	</script>
  </head>
  
  <body>
   <h3>修改用户密码</h3>
   	 <form action="" method="post" name="actionform">
   	 	<table cellpadding="0" cellspacing="0">
   	 		<tr>
   	 			<th align="right">旧密码：</th><td><input type="password" name="oldPassword" id="oldPassword" maxlength="20"/></td>
   	 		</tr>
   	 		<tr>
   	 			<th align="right">新密码：</th><td><input type="password" name="newPassword" id="newpassword" maxlength="20"/></td>
   	 		</tr>
   	 		<tr>
   	 			<th align="right">确认密码：</th><td><input type="password" name="repwd" id="repwd" maxlength="20"/></td>
   	 		</tr> 	 		
   	 		
   	 		<tr>
   	 			<td align="center" colspan="3">
   	 				<input type="button" value="确定" onclick="javascript:update();"/>
   	 				<input type="reset" value="重置"/>
   	 			</td>
   	 		</tr>
   	 	</table>
   	 </form>
   
  </body>
</html>
