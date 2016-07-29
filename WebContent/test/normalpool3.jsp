<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	request.setAttribute("ctx", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>长连接ajax方式</title>
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript">
	$(function() {
		freshlogs();
	});

	function freshlogs() {
		$('#logs').prepend('go<br>');
		$.ajax({
			type : "post",
			url : "${ctx}/mysecservlet/getlogs.html?flag=" + Math.random(),
			timeout : 30000,
			data : "",
			success : function(data) {
				$('#logs').prepend(data);
			},
			complete:function(XMLHttpRequest,status){
				if(status=='timeout'){
					$('#logs').prepend('timeout<br>');
				}
			}
		});
	}
</script>
</head>
<body>
	<div id='logs'></div>
</body>
</html>