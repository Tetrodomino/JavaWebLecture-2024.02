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
<body style="margin: 50px; margin-bottom: 100px;">
	<h1>Expression Language Operator(연산자)</h1>
	<hr>
	<%--<table border="1">
		<tr>
			<th>표현식</th>
			<th>결과</th>
		</tr>
		<tr>
			<td>\${100}</td> <!-- 중괄호랑 $을 그냥 출력시키고 싶으면 앞에 \ 을 붙이기 -->
			<td>${100}</td>
		</tr>
		<tr>
			<td>\${"안녕하세요"}</td> <!-- 문자열 출력 -->
			<td>${"안녕하세요"}</td>
		</tr>
		<tr>
			<td>\${10 + 1}</td>
			<td>${10 + 1}</td> <!-- 계산 결과가 출력(숫자끼리의 계산만 허용) -->
		</tr>
		<tr>
			<td>\${"10" + 1}</td> <!-- 문자열 10이 숫자 10으로 취급되어서 계산됨 -->
			<td>${10 + 1}</td>
		</tr>
		<tr>
			<td>\${null + 1}</td> 
			<td>${null + 1}</td> <!-- null은 0으로 취급 -->
		</tr>
		<tr>
			<td>\${"a" + "b"}</td> 
			<td>${"a" + "b"}</td> <!-- 숫자 외 대상의 계산은 허용되지 않음 -->
		</tr>
		<tr>
			<th colspan="2">${"사칙연산"}</th> 
		</tr>
		<tr>
			<td>\${10 - 2}</td>
			<td>${10 - 2}</td>
		</tr>
		<tr>
			<td>\${10 * 2}</td>
			<td>${10 * 2}</td>
		</tr>
		<tr>
			<td>\${10 / 3}</td>
			<td>${10 / 3}</td> <!-- 소숫점 자리까지 계산됨 -->
		</tr>
		<tr>
			<td>\${10 div 3}</td>
			 <!-- 10 / 3과 동일 -->
		</tr>
		<tr>
			<td>\${10 % 3}</td>
			<td>${10 % 3}</td>
		</tr>
		<tr>
			<td>\${10 mod 3}</td>
			<td>${10 mod 3}</td>
		</tr>
		<tr>
			<th colspan="2">${"비교 연산자"}</th>
		</tr>
		<tr>
			<td>\${10 == 2}</td>
			<td>${10 == 2}</td>
		</tr>
		<tr>
			<td>\${10 eq 2}</td>
			<td>${10 eq 2}</td> <!-- 10 == 2와 동일 -->
		</tr>
		<tr>
			<td>\${"hello" == "hello"}</td>
			<td>${"hello" == "hello"}</td> <!-- 문자열 비교도 가능 -->
		</tr>
		<tr>
			<td>\${"hello" != "world"}</td>
			<td>${"hello" != "world"}</td>
		</tr>
		<tr>
			<td>\${"hello" ne "world"}</td>
		 	 <!-- ne는 !=와 동일 -->
		</tr>
		<tr>
			<td>\${10 > 2}</td>
			<td>${10 > 2}</td>
		</tr>
		<tr>
			<td>\${10 gt 2}</td>
			<td>${10 gt 2}</td>
			<!--
					gt : >
					ge : >=
					lt : <
					le : <=
			-->
		</tr>
		<tr>
			<th colspan="2">${"논리 연산자"}</th> 
		</tr>
		<tr>
			<td>\${10 >= 8 && 4 <= 3}</td>
			<td>${10 >= 8 && 4 <= 3}</td>
		</tr>
		<tr>
			<td>\${10 ge 8 and 4 le 3}</td>
			<td>${10 ge 8 and 4 le 3}</td>
		</tr>
		<tr>
			<td>\${!(10 ge 8 and 4 le 3)}</td>
			<td>${!(10 ge 8 and 4 le 3)}</td>
		</tr>
		<tr>
			<td>\${not (10 ge 8 and 4 le 3)}</td>
			<td>${not (10 ge 8 and 4 le 3)}</td>
		</tr>
		<tr>
			<td>\${10 ge 8 or 4 le 3}</td>
			<td>${not (10 ge 8 and 4 le 3)}</td>
		</tr>
		<tr>
			<th colspan="2">${"문자열 연산자"}</th> 
		</tr>
		<tr>
			<td>\${empty "hello"}</td>
			<td>${empty "hello"}</td> <!-- 글자가 있으면 false -->
		</tr>
		<tr>
			<td>\${empty ""}</td>
			<td>${empty ""}</td>	<!-- 빈 문자열은 true -->
		</tr>
		<tr>
			<td>\${empty null}</td>
			<td>${empty null}</td>	<!-- null 도 true -->
		</tr>
	</table>
	<!-- param : 주소창에 입력된 매개변수를 의미
		 표현식 안에서 문자열은 '' 와 "" 둘 다 사용 가능 -->
	<h3>입력한 마라메터 값은 ${empty param.num ? "입력하지 않았음" : param.num}</h3> --%>
</body>
</html>