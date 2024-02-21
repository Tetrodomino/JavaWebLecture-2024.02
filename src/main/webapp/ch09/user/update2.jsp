<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 변경</title>
</head>
<body style="margin: 50px;">
	<h1>회원 정보 변경</h1>
	<hr>
	<form action="/jw/ch09/user/update" method="post">
		<input type="hidden" name="id" value="${user.uid}">
		<input type="text" value="${user.uid}" disabled><br>
		<input type="text" name="pwd" value="${user.pwd}"><br>
		<input type="text" name="pwd2" placeholder="패스워드 확인"><br>
		<input type="text" name="name" value="${user.name}"><br>
		<input type="text" name="email" value="${user.email}"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>