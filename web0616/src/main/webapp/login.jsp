<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆界面</title>
</head>
<body>
	<form action="user">
	<input type="text" class="text" name="type" value="login">
		用户名<input type="text" class="text" name="user_name"> 密码 <input
			type="password" name="user_password"> <input type="submit"
			value="登陆">


		<div class="new">
			<p class="sign">
				没有账号 <a href="sign1.jsp">注册</a>
			</p>
			<div class="clearfix"></div>
		</div>
		<c:out value="${msg }"></c:out>
	</form>
</body>
</html>