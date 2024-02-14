<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>
<style>
	td { padding: 3px; }
</style>
</head>
<body>
	<!-- 연결된 파일은 Ex05_login -->
	<h1>로그인 시도 결과</h1>
	<hr>
	<h2>${result}</h2>
	<!-- <table>
		<tr>
			<td>아이디</td>
			<td>${uid}</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td>${pwd}</td>
		</tr>
	</table> -->
	<button onclick="location.href='/jw/ch06/login'">재실행</button>
</body>
</html>