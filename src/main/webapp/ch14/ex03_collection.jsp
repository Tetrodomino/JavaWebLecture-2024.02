<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1>Collection(컬렉션)</h1>
	<hr>
	<table border="1">
		<tr>
			<th colspan="2">어레이Array</th>
		</tr>
		<tr>
			<td>\${array[0]}</td>
			<td>${array[0]}</td>
		</tr>
		<tr>
			<td>\${array[4]}</td>
			<td>${array[4]}</td>
		</tr>
		<tr>
			<th colspan="2">리스트</th>
		</tr>
		<tr>
			<td>\${list[0]}</td>
			<td>${list[0]}</td>
		</tr>
		<tr>
			<td>\${list[3]}</td>
			<td>${list[3]}</td>
		</tr>
		<tr>
			<td>\${list.get(1)}</td>
			<td>${list.get(1)}</td>
		</tr>
		<tr>
			<th colspan="2">맵</th>
		</tr>
		<tr>
			<td>\${map.key}</td>
			<td>${map.key}</td> <!-- 맵의 데이터를 가져올 때는 key 값을 가져와야 함 (여기서는 key 값이 key, value)-->
		</tr>
		<tr>
			<td>\${map.value}</td>
			<td>${map.value}</td>
		</tr>
	</table>
</body>
</html>