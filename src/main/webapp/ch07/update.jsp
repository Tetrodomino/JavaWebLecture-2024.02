<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도시 정보 변경</title>
</head>
<body style="margin: 50px;">
	<h1>도시 정보 변경</h1>
	<hr>
	<form action="/jw/ch07/city/update" method="post">
	
		<input type="hidden" name="id" value="${city.id}"><br> <!-- hidden은 보이지 않으며 데이터만 전달함 -->
		<input type="text" value="${city.id}" disabled><br> <!-- 데이터 전달 기능 없이 보여주기만 하는 용도 -->
		<input type="text" name="name" value="${city.name}"><br>
		<input type="text" name="countryCode" value="${city.countryCode}"><br>
		<input type="text" name="district" value="${city.district}"><br>
		<input type="text" name="population" value="${city.population}"><br>
		<input type="submit" value="제출">
	</form>
</body>
</html>