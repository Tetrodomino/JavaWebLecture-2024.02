<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 정보 변경</title>
</head>
<body style="margin: 50px;">
	<h1>아티스트 정보 변경</h1>
	<hr>
	<form action="/jw/ch07/kpop/updateartist" method="post">
		<input type="hidden" name="id" value="${artist.aid}">
		<input type="text" value="${artist.aid}" disabled><br>
		<input type="text" name="name" value="${artist.name}"><br>
		<input type="text" name="debut" value="${artist.debut}"><br>
		<input type="text" name="hit_song_id" value="${artist.hit_song_id}"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>