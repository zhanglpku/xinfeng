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
	function queryFun(){
		document.getElementById("versionResultDiv").innerHTML = "";
		$.post("<%=path%>/sys/getVersion",
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
								inHtml += "<td width=\"160\" align=\"center\">" + obj.version + "</td>";	
								inHtml += "<td>" + obj.commands + "</td>";	
								inHtml += "<td width=\"80\" align=\"center\"><a href=\"javascript:deleteVersion(" + obj.id +");\">删除</a></td>";
								inHtml += "</tr>"
							}
							document.getElementById("versionResultDiv").innerHTML = inHtml;
						}
					}
				}
		 }); 
	}
	function deleteVersion(id){
		if(null == id || "" == id)
			return;
		$.post("<%=path%>/sys/deleteVersion",{id:id},
				function(data){
					if("" != data){
						var json = eval('(' + data + ')');
						var state = json.state;
						if(state){
							queryFun();
						}else{
							alert(json.description);
						}
					}
			 }); 
	}
	function addFun(){
		//document.getElementById("addVersionID").style.display="";
	    var ret = window.showModalDialog("addVersion.jsp", null,
				"dialogWidth:350px;dialogHeight:350px;dialogTop:100;dialogLeft:200;"+
	    		"help:no;status:no;center:yes;resizable：no;scroll:no;");
		if(ret) {
			queryFun();
		}
	}
</script>
</head>
<body>
	<div style="position: absolute; top:0px;left:0px;width:100%;height:60px;">
	<!-- 列表  表头-->
		<table class="tableList">
			<tr>
				<td width="40" colspan="4" align="right">
					<button onClick="queryFun()">&nbsp;&nbsp;查&nbsp;询&nbsp;&nbsp;</button>
					<button onclick="addFun();">&nbsp;&nbsp;新&nbsp;增&nbsp;&nbsp;</button>
				</td>
			</tr>
			<tr>
				<th width="40">序号</th>
				<th width="160">型号</th>
				<th>命令</th>
				<th width="80">操作</th>
			</tr>
		</table>
	</div>
	<div style="position: absolute; top:66px;left:0px;width:100%;">
	<!-- 列表 内容 -->
		<table id="versionResultDiv" class="tableList">
						 
		</table>
	</div>
	<div id="addVersionID" style="position: absolute; top:100px;left:100px;z-index:10;display:none;">
		<form name="actionForm" method="post" >
			型号：<input name="version" type="text"><br/>
			命令：
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				<input name="commands" type="checkbox" value="" />苹果
				
			<input type="button" value="查询" onclick="submitFun();">
						<input type="reset" value="重置">
		</form>
	</div>
</body>
</html>