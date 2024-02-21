<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

<c:set var="price" value="123456000"></c:set>
<c:set var="now" value="<%= new Date()%>"></c:set>

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
			<td>일반 숫자</td>
			<td>${price}</td> <!-- 위에서 c:set으로 정의된 price 값을 가져옴 -->
		</tr>
		<tr> <!-- fmt의 formatNumber로 다양한 방식으로 숫자 표시 -->
			<td>천 단위 구분 숫자</td>
			<td><fmt:formatNumber type="number" value="${price}"></fmt:formatNumber></td>
		</tr>
		<tr>
			<td>천 단위 구분 숫자(type 생략)</td>
			<td><fmt:formatNumber type="number" value="${price}"></fmt:formatNumber></td>
		</tr>
		<tr>
			<td>통화(한국 원)</td>
			<td><fmt:formatNumber type="currency" currencySymbol="KRW" value="${price}"></fmt:formatNumber></td>
		</tr>
		<tr>
			<td>통화(미국 달러)</td>
			<td><fmt:formatNumber type="currency" currencySymbol="USD" value="${price}"></fmt:formatNumber></td>
		</tr>
		<tr>
			<td>퍼센트</td>
			<td><fmt:formatNumber type="percent" value="${price / 100000000}"></fmt:formatNumber></td>
		</tr>
		<tr>
			<th colspan="2">날짜</th>
		</tr>
		<tr>
			<td>일반 날짜</td>
			<td>${now}</td> <!-- Wed Feb 21 14:00:10 KST 2024 -->
		</tr>
		<tr>
			<td>전체 날짜(full date)</td> <!-- 2024년 2월 21일 수요일 --> 
			<td><fmt:formatDate type="date" dateStyle="full" value="${now}"></fmt:formatDate></td>
		</tr>
		<tr>
			<td>짧은 날짜(short date)</td> <!-- 24. 2. 21. -->
			<td><fmt:formatDate type="date"  dateStyle="short" value="${now}"></fmt:formatDate></td>
		</tr>
		<tr>
			<td>시간 time</td> <!-- 오후 2:03:54 -->
			<td><fmt:formatDate type="time" value="${now}"></fmt:formatDate></td>
		</tr>
		<tr>
			<td>패턴(YYYY-MM-DD HH:mm:ss)</td> <!-- 2024-02-52 14:05:11 -->
			<td><fmt:formatDate pattern="YYYY-MM-DD HH:mm:ss" value="${now}"></fmt:formatDate></td>
		</tr>
		<tr>
			<td>패턴(YYYY-MM-DD HH:mm)</td> <!-- 2024-02-52 14:05 -->
			<td><fmt:formatDate pattern="YYYY-MM-DD HH:mm" value="${now}"></fmt:formatDate></td>
		</tr>
	</table>
</body>
</html>