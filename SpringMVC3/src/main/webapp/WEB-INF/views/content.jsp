<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!-- == controller -->
<!-- c:set 저장영역에 객체 저장  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 지정 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="jumbotron">
		<h1>빅데이터 23차 게시판</h1>
		<p>Bootstrap을 사용하여 간단하게 게시판을 만들어보자</p>
	</div>

	<div class="container">
		<div class="card">
			<!-- 띄어쓰기 두 번 : 클래스 명 두 개 -->
			<!-- 게시글 하나만 보니까 foreach문 필요없음 -->
			<div class="card-header">상세보기</div>
			<div class="card-body">
				<table class="table table-bordered table-hover">
					<tr>
						<td>제목</td>
						<td>${board.title}</td>
					</tr>

					<tr>
						<td>내용</td>
						<td>${board.content}</td>
					</tr>

					<tr>
						<td>작성자</td>
						<td>${board.writer}</td>
					</tr>

					<tr>
						<td>작성일</td>
						<td>${board.indate}</td>
					</tr>
					<tr>
						<td colspan=2>
						<button type="button" class="btn btn-primary">수정</button>
						<button onclick="location.href='${cpath}/boardDelete/${board.idx}'" type="button" class="btn btn-secondary">삭제</button>
						<button type="button" class="btn btn-success">리스트</button>
						</td>
					</tr>
					



				</table>
				
			</div>
		</div>
	</div>



</body>
</html>