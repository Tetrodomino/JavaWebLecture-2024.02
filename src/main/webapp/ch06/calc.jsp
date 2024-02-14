<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple calc</title>
</head>
<body>
	<!-- Ex03_calculator2와 연동 -->
	<!-- jsp 파일을 사용하여 페이지를 바꾸지 않고 결과 출력시키기 -->
	<h1>simple calculator</h1>
	<hr>
	<form action="/jw/ch06/calc2" method="post">
		<input type="text" name="num1" value="${num1}">	
		<select name="op">
			<option value="+" selected>+</option>
			<option value="-">-</option>
			<option value="*">×</option>
			<option value="/">÷</option>
		</select>
		<input type="text" name="num2" value="${num2}">	
		<input type="submit" value="=">
		<input type="text" name="result" value="${result}" disabled>
	</form>
	<br>
	<!-- 버튼을 눌러 처음 상태로 돌아가기(방법 1) -->
	<button onclick="location.href='/jw/ch06/calc2'">처음으로</button>
</body>
</html>