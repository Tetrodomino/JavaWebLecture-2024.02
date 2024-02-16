<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Korean City list</title>
<style>
	th, td {padding: 3px;}
</style>
</head>
<body style="margin: 50px;">
	<h1>국내 도시 목록
		<button style="margin-left: 100px;" onclick="location.href='/jw/ch07/kcity/insert'">추가</button>
	</h1>
	<hr>
	<table border="1">
		<tr>
			<th>id</th>
			<th>도시명</th>
			<th>국가코드</th>
			<th>지역명</th>
			<th>인구수</th>
			<th>액션</th>
		</tr>
		<!-- jstl을 쓰면 html에서 반복문을 사용할 수 있음 -->
		<c:forEach var="city" items="${list}"> <!-- for (city c: list)와 동일 -->
			<tr>
				<td>${city.id}</td> <!-- ch07_dao/kcity 에 있는 City 클래스의 변수명 id를 사용 -->
				<td>${city.name}</td>
				<td>${city.countryCode}</td>
				<td>${city.district}</td>
				<td>${city.population}</td>
				<td> <!-- 도시의 데이터를 수정 및 삭제하는 버튼을 만드는 공간 -->
					<a href="/jw/ch07/kcity/update?id=${city.id}">수정</a>
					<a href="/jw/ch07/kcity/delete?id=${city.id}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>