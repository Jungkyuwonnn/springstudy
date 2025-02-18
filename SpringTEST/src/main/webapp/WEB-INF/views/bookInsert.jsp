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
	  <h1>도서 입력</h1>
	</div>
	
	<div class="container">
		<div class="card">
			<div class="card-header"></div>
			<div class="card-body">
				<form action="${cpath}/bookInsert.do" method="post">
					<div class="form-group">
						<label for="title">제목:</label> 
						<input name="title" type="text" class="form-control" id="title">
					</div>
					<div class="form-group">
						<label for="author">작가:</label> 
						<input name="author" type="text"
							class="form-control" id="author">
					</div>
					<div class="form-group">
						<label for="company">출판사:</label>
						<input name="company" type="text" class="form-control" id="company">
					</div>
					<div class="form-group">
						<label for="isbn">ISBN:</label>
						<input name="isbn" type="text" class="form-control" id="isbn">
					</div>
					<div class="form-group">
						<label for="count">보유도서 수:</label>
						<input name="count" type="text" class="form-control" id="count">
					</div>
					<button type="submit" class="btn btn-primary">등록</button>
				</form>
			</div>
		</div>
	</div>
	

	
</body>
</html>