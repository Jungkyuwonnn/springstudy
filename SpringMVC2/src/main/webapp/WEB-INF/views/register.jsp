<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>

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
			<!-- 디자인은 클래스명 기준으로 설정됨  -->
			<!-- name을 DB 설정한 컬럼과 같게 하기 -->
			<div class="card-header">Panel Heading</div>
			<div class="card-body">
				<form action="${cpath}/register" method="post">
				<!-- register2 : 글쓰기 등록 -->
					<div class="form-group">
						<label for="title">제목:</label> 
						<input name="title" type="text" class="form-control" placeholder="제목 입력" id="title">
					</div>
					<div class="form-group">
						<label for="writer">작성자:</label> 
						<input name="writer" type="text"
							class="form-control" placeholder="작성자 입력" id="writer">
					</div>
					<div class="form-group">
						<label for="pwd">내용:</label>
						<textarea name="content" class="form-control" rows="10" id="content"></textarea>
					</div>
					<button type="submit" class="btn btn-primary">작성하기</button>
				</form>
			</div>
		</div>
	</div>



</body>
</html>