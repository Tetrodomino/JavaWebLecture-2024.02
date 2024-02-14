<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body style="margin: 50px;">
	<h1>회원가입하기</h1>
	<hr>
	<form action="/jw/ch06/register" method="post">
		<input type="text" name="uid" placeholder="id 입력"><br>
		<input type="password" name="pwd" placeholder="password 입력"><br>
		<input type="password" name="pwd2" placeholder="password 확인"><br>
		<input type="text" name="name_reg" placeholder="이름 입력"><br>
		<input type="text" name="email" placeholder="이메일 입력"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>