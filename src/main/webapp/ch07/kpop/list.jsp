<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kpop star list</title>
<style>
	th, td {padding: 3px;}
</style>
</head>
<body style="margin: 50px;">
	<h1>Kpop 걸그룹 모음
		<button style="margin-left: 100px;" onclick="location.href='/jw/ch07/kpop/insertartist'">아티스트 추가</button>
		<button onclick="location.href='/jw/ch07/kpop/insertsong'">노래 추가</button>
	</h1>
	<hr>
	<table border="1">
		<tr>
			<th>id</th>
			<th>그룹명</th>
			<th>데뷔일자</th>
			<th>히트곡</th>
			<th>가사</th>
			<th>삭제</th>
		</tr>
		<!-- jstl을 쓰면 html에서 반복문을 사용할 수 있음 -->
		<c:forEach var="kpop" items="${list}"> <!-- for (city c: list)와 동일 -->
			<tr>
				<td>${kpop.aid}</td>
				<td><a href="/jw/ch07/kpop/updateartist?id=${kpop.aid}">${kpop.name}</a></td>
				<td>${kpop.debut}</td>
				<td><a href="/jw/ch07/kpop/updatesong?id=${kpop.sid}">${kpop.title}</a></td>
				<td>${kpop.lyrics}</td>
				<td> <!-- 도시의 데이터를 수정 및 삭제하는 버튼을 만드는 공간 -->
					<a href="/jw/ch07/kpop/deleteartist?id=${kpop.aid}">아티스트</a>
					<a href="/jw/ch07/kpop/deletesong?id=${kpop.sid}">노래</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>