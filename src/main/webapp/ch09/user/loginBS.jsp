<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/_head.jspf" %>
	<style>
		input { margin: 5px;}
		td { text-align: center; }
	</style>
</head>
<body class="bg-light">
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container-fluid">
      <img src="/jw/img/ck-logo.png" height="60">
      <div class="p-2 bg-dard justify-content-center">
        <img src="https://picsum.photos/1500/180" width="100%">
      </div>
    </div>
  </nav>
    <div class="container" style="margin-top: 240px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title"><h3><strong>로그인</strong></h3></div>
            <hr>
            <!-- action의 링크를 주의, enctype은 파일을 보낼 때 사용 -->
            <form action="/jw/ch09/user/login" method="post">
              <table class="table table-borderless">
                <tr>
                  <td style="width: 40%;"><label class="col-form-label">사용자 ID</label></td>
                  <td style="width: 60%;"><input type="text" name="uid" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">패스워드</label></td>
                  <td><input type="password" name="pwd" class="form-control"></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">확인</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3">
              <span class="me-3">사용자 계정이 없으신가요?</span>
              <a href="/jw/ch09/user/register">회원가입</a>
            </p>
            <div class="mt-3 mb-3">
              <span class="me-3">소셜 계정으로 로그인</span>
              <span>
                <a class="ms-2" href="#"><img src="/jw/img/google-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/github-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/naver-logo.jpg" height="32"></a>
                <a class="ms-2" href="#"><img src="/jw/img/kakao-logo.png" height="32"></a>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  <%@ include file="../../common/_bottom.jspf" %>
</body>
</html>