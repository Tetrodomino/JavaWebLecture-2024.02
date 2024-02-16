<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아티스트 추가</title>
</head>
<body style="margin: 50px;">
	<h1>아티스트 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertartist" method="post">
		<input type="text" name="name" placeholder="그룹 이름"><br>
		<input type="text" name="debut" placeholder="데뷔일자"><br>
		<input type="text" name="hit_song_id" placeholder="히트곡 번호"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>