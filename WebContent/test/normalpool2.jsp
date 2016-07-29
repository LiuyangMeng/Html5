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
<title>普通轮询iframe方式</title>
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript">
	$(function() {
		window.setInterval(freshlogs2, 10000);
	});

	function freshlogs2() {
		$("#logs").prepend(
				$($("#pooling").get(0).contentDocument).find("body").text());
		$("#pooling").attr("src",
				"${ctx}/mysecservlet/getlogs.html?flag=" + Math.random());
		// 延迟1秒再重新请求
		window.setTimeout(function() {
			window.frames["pooling"].location.reload();
		}, 1000);
		alert('');
		//$("#logs").prepend($($("#pooling").get(0).contentDocument).find("body").text());

	}
</script>
</head>
<body>
	<iframe id="pooling" name="pooling" style="display: none;"></iframe>
	<div id='logs'></div>
</body>
</html>