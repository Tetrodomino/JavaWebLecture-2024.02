<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hello" value="Hello World!"></c:set>
<c:set var="mall" value="쇼핑몰의 중심 JSP Mall!"></c:set>
<c:set var="center" value="중심"></c:set>
<c:set var="local" value="2024-02-21T14:23:00"></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL</title>
	<style>
		td, th {padding: 3px;}
	</style>
</head>
<body style="margin: 50px;">
	<h1>JSTL(JSP Standard Tag Library) - Formatting</h1>
	<hr>
	<table border="1">
		<tr>
			<th>표현 방법</th>
			<th>결과</th>
		</tr>
		<tr>
			<th colspan="2">숫자</th>
		</tr>
		<tr>
			<td>문자열 연결</td>
			<td>${hello} ${mall}</td> <!-- 위에서 c:set으로 정의된 hello와 amll 값을 연달아 가져옴 -->
		</tr>
		<tr>
			<td>\${fn:length(mall)}</td>
			<td>${fn:length(mall)}</td> <!-- 문자열의 길이 -->
		</tr>
		<tr>
			<td>\${fn:toUpperCase(hello)}</td>
			<td>${fn:toUpperCase(hello)}</td> <!-- 대문자로 변경 -->
		</tr>
		<tr>
			<td>\${fn:substring(mall, 5, 7)}</td>
			<td>${fn:substring(mall, 5, 7)}</td> <!-- 6, 7번째 글자만 슬라이스 -->
		</tr>
		<tr>
			<td>\${fn:replace(mall, " ", "==")}</td>
			<td>${fn:replace(mall, " ", "==")}</td> <!-- 문자열 내의 특정 문자열 일괄 변경 -->
		</tr>
		<tr>
			<td>\${fn:indexOf(mall, center)}</td>
			<td>${fn:indexOf(mall, center)}</td> <!-- 문자열의 길이 -->
		</tr>
		<tr>
			<td>\${fn:contains(mall, center)}</td>
			<td>${fn:contains(mall, center)}</td> <!-- 문자열의 길이 -->
		</tr>
	</table>
	
	<%-- local을 24-02-21 14:23 으로 바꾸기 --%>
	<h3>${fn:substring(fn:replace(local, "T", " "), 2, 16)}</h3>
</body>
</html>