<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("name", "페이지 변수"); // jsp 파일에 직접 작성한 것, 맨 아래 pageScope에 적용
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>표현 언어(EL)</title>
	<style>
		td, th {padding: 3px;}
	</style>
</head>
<body style="margin: 50px;">
	<h1>Built-in object(내장 객체)</h1>
	<hr>
	<table border="1">
		<tr>
			<th>표현식</th>
			<th>결과</th>
		</tr>
		<tr>
			<td>\${empty param.id ? 10 : param.id}</td>
			<td>${empty param.id ? 10 : param.id}</td>
		</tr>
		<tr>
			<td>\${applicationScope.aName}</td>
			<td>${applicationScope.aName}</td>
		</tr>
		<tr>
			<td>\${sessionScope.aName}</td>
			<td>${sessionScope.sName}</td>
		</tr>
		<tr>
			<td>\${requestScope.rName}</td>
			<td>${requestScope.rName}</td>
		</tr>
		<tr>
			<td>\${requestScope.name}</td>
			<td>${requestScope.name}</td> <!-- 리퀘스트에서 정의된 Name 속성이 나옴 -->
		</tr>
		<tr>
			<td>\${pageScope.name}</td>
			<td>${pageScope.name}</td>
		</tr>
		<tr>
			<th colspan="2">${"스코프 생략"}</th> <!-- 그냥 SetAttribute 된 속성값만 써서 출력 -->
		</tr>
		<tr>
			<td>\${aName}</td>
			<td>${aName}</td>
		</tr>
		<tr>
			<td>\${sName}</td>
			<td>${sName}</td>
		</tr>
		<tr>
			<td>\${rName}</td>
			<td>${rName}</td>
		</tr>
		<tr>
			<td>\${name}</td>
			<td>${name}</td>
		</tr>
		<tr>
			<th colspan="2">${"헤더 정보"}</th> 
		</tr>
		<tr>
			<td>\${header.host}</td>
			<td>${header.host}</td>
		</tr>
		<tr>
			<td>\${header.cookie}</td>
			<td>${header.cookie}</td>
		</tr>
		<tr>
			<td>\${header["User-Agent"]}</td>
			<td>${header["User-Agent"]}</td>
		</tr>
		<tr>
			<th colspan="2">${"쿠키 정보"}</th> 
		</tr>
		<tr>
			<td>\${cookie.JSESSIONID.name}</td>
			<td>${cookie.JSESSIONID.name}</td> <!-- 쿠기의 이름 -->
		</tr>
		<tr>
			<td>\${cookie.JSESSIONID.value}</td>
			<td>${cookie.JSESSIONID.value}</td> <!-- 쿠기의 값 -->
		</tr>
	</table>
</body>
</html>