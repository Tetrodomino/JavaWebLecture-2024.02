<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노래 추가</title>
</head>
<body style="margin: 50px;">
	<h1>노래 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertsong" method="post">
		<input type="hidden" name="id" value="${sid}">
		<input type="text" value="${sid}" disabled><br>
		<input type="text" name="title" placeholder="곡명"><br>
		<input type="text" name="lyrics" placeholder="가사"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>