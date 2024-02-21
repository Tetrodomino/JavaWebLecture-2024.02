<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert Message</title>
</head>
<body>
	<!-- ch09의 user와 연결 -->
	<script>
		let msg = '${msg}';
		let url = '${url}';
		alert(msg);
		location.href = url;
	</script>
</body>
</html>