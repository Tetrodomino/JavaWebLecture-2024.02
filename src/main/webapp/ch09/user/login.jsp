<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사용자 관리</title>
	<style>
		input { margin: 5px;}
	</style>
</head>
<body style="margin: 50px;">
	<h1>
		로그인
		<button style="margin-left: 100px" onclick="location.href='/jw/ch09/user/register'">회원가입</button>
	</h1>
	<hr>
	<form action="/jw/ch09/user/login" method="post">
		<input type="text" name="uid" placeholder="id 입력" autofocus><br>
		<input type="password" name="pwd" placeholder="패스워드 입력"><br>
		<input type="submit" value="로그인"><br>
	</form>
</body>
</html>