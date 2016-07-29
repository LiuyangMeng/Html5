<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线人数</title>
<script type="text/javascript">
	
	function likai() {
		alert('窗口即将关闭');
	}
</script>
</head>
<body onunload="likai1();">
	<h4>你好:</h4>
	在线人数为:<%=request.getSession().getServletContext().getAttribute("onlinecount")%>
</body>
</html>