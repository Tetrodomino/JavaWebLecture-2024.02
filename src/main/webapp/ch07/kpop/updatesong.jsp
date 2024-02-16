<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노래 정보 변경</title>
</head>
<body style="margin: 50px;">
	<h1>노래 정보 변경</h1>
	<hr>
	<form action="/jw/ch07/kpop/updatesong" method="post">
		<input type="hidden" name="id" value="${song.sid}">
		<input type="text" value="${song.sid}" disabled><br>
		<input type="text" name="title" value="${song.title}"><br>
		<input type="text" name="lyrics" value="${song.lyrics}"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>