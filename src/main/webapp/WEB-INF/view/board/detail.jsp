<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newline", "\n"); %>


<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/_head.jspf" %>
	<style>
		th {padding: 3px; text-align: center;}
		td {padding: 3px; text-align: center;}
		.disabled-link { pointer-events: none; }
	</style>
	<script>
		function deleteFunc(bid) {
			$('#deleteBoard').val(bid); // id값이 deleteBoard인 곳의 value를 bid로 설정
			$('#deleteModal').modal('show'); // id값이 deleteModal인 것을 찾아서 show를 모달함
		}
	</script>
</head>
<body>
	<%@ include file="../common/_top.jspf" %>
	<div class="container" style="margin-top:80px">
    	<div class="row">
			<%@ include file="../common/_aside.jspf" %>
			
			<!-- //// 본문 영역 //// -->
			<div class="col-9">
				<h3><strong class="me-5">게시글 조회</strong>
					<span style="font-size: 16px;">
						<a href="/jw/bbs/board/list?p=${currentBoardPage}&f=${field}&q=${query}"><i class="fa-solid fa-table-list ms-3"></i> 목록</a>
					<c:if test="${sessUid eq board.uid or sessUid eq 'admin'}">
						<!-- 본인만 수정 삭제 가능 -->
						<a href="/jw/bbs/board/update?bid=${board.bid}"><i class="fa-solid fa-pen ms-3"></i> 수정</a>
						<a href="javascript:deleteFunc('${board.bid}')"><i class="fa-solid fa-trash ms-3"></i> 삭제</a>
					</c:if>
					<c:if test="${sessUid ne board.uid and sessUid ne 'admin'}">
						<a href="#" class="disabled-link"><i class="fa-solid fa-pen"></i> 수정</a>
						<a href="#" class="disabled-link"><i class="fa-solid fa-trash ms-3"></i> 삭제</a>
					</c:if>
					</span>
				</h3>
       			<hr>
       			<div class="row">	
			       	<div class="col-8">
			       		<h5>${board.title }</h5>
			       		<h6>글번호: ${board.bid} | ${fn:replace(board.modTime, "T", " ")}</h6>
			       	</div>
			       	<div class="col-4 text-end">
			       		<h5>${board.uname}</h5>
			       		<h6>조회 ${board.viewCount} &nbsp;&nbsp;&nbsp; 댓글 ${board.replyCount}</h6>
			       	</div>
			       	<hr>
					<div class="col-12">
						${fn:replace(board.content, newline, '<br>') } <!-- 게시글 줄바꿈 시켜주기 -->
					</div>			       	
       			</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/_bottom.jspf" %>
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
		    <div class="modal-content">
			    <!-- Modal Header -->
			    <div class="modal-header">
			      	<h4 class="modal-title">글 삭제</h4>
			      	<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			    </div>
			
			    <!-- Modal body -->
		      	<div class="modal-body">
		        	<strong>삭제하시겠습니까?</strong>
		        	<div class="text-center mt-5">
		        		<form action="/jw/bbs/board/delete" method="post">
		        			<input type="hidden" id="deleteBoard" name="bid">
			        		<button class="btn btn-danger" type="submit">삭제</button>
		        		</form>
		        	</div>
		      	</div>
		    </div>
		</div>
	</div>
</body>
</html>