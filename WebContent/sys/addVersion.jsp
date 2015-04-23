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
		var version = document.getElementById("versionID").value;
		if(null == version || "" == version){
	    	alert("请填写型号！");
	    	return false;
	    }
		var commands = "";
		var ary=document.getElementsByName("commands"); 
	    for(var i=0;i<ary.length;i++){
	         if(ary[i].checked){
	        	 //alert(ary[i].value+","+ary[i].nextSibling.nodeValue);
		         commands += ary[i].value + ",";
	         }
	    }     
	   if("" == commands){
	    	alert("请选择命令！");
	    	return false;
	    }
	    commands = commands.substr(0,commands.length-1);
	    
		$.post("<%=path%>/sys/addVersion",
			{version : version,commands: commands},
			function(data){
				if("" != data){
					var json = eval('(' + data + ')');
					var state = json.state;
					if(state){
						window.returnValue = true;
					}else{
						window.returnValue = false;
					}
					window.close();
				}
		 }); 
	}
</script>
</head>
<body>
	<div style="position: absolute; top:10px;left:20px;width:100%;height:60px;">
		<form name="actionForm" method="post" >
			型号：<input id="versionID" name="version" type="text"/><br/>
			命令：<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_MODEL%>" />自动手动<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_AIRLOOP%>" />内外循环<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_SLEEP%>" />睡眠<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_HEAT%>" />加热<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_STATICELECTRICITY%>" />静电<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_TIME%>" />定时<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input name="commands" type="checkbox" value="<%=Constant.CONSTANT_COMMAND_ONOROFF%>" />电源开关<br/>
			<br/>	
			<input type="button" value="确定" onclick="submitFun();"/>
			<input type="reset" value="重置"/>
		</form>
	</div>
</body>
</html>