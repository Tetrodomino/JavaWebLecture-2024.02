<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body style="margin: 50px">
	<!-- 연결된 파일은 Ex05_login -->
	<h1>로그인</h1>
	<hr>
	<form action="/jw/ch06/login" method="post">
		<input type="text" name="uid" placeholder="id 입력"><br>
		<input type="password" name="pwd" placeholder="패스워드 입력"><br>
		<input type="submit" value="login">
	</form>
</body>
</html>