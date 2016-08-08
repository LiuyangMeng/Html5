<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用cookie登陆系统</title>
</head>
<body>
	<form action="/TestServer/login/loginUser.do" method="post">
		<!-- 用户登陆框 -->
		<div align="center">
			<div>
				<label>用户名:</label><input type="text" name="username" id="username"
					value="<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>"
					tabindex="1" />
			</div>
			<div>
				<label>密&nbsp;&nbsp;&nbsp;码:</label><input type="password"
					name="userpwd" id="userpwd"
					value="<%=request.getAttribute("userpwd") == null ? "" : request.getAttribute("userpwd")%>"
					tabindex="2" />
			</div>
			<div>
				<input type="checkbox" name="rempwd" id="rempwd"
					<%=request.getAttribute("rempwd").equals("1") ? "checked='checked'" : ""%>
					tabindex="3" value="1" /><label>记住密码</label>
			</div>
			<div>
				<input type="submit" id="submit1" value="提交" /><input type="reset"
					id="reset1" value="重置" />
			</div>
		</div>
	</form>
</body>
</html>