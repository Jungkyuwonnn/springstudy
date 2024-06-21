<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!-- == controller -->
<!-- c:set 저장영역에 객체 저장  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 지정 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="jumbotron">
	  <h1>빅데이터 23차 게시판</h1>
	  <p>Bootstrap을 사용하여 간단하게 게시판을 만들어보자</p>
	</div>
	
	<div class="container">
		<div class="card"> <!-- 띄어쓰기 두 번 : 클래스 명 두 개 -->
			<div class="card-header">게시판 연습</div>
			<div class="card-body">
				<table class="table table-bordered table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
					</tr>
					
					<!-- JSTL/El 사용해서 request 영역 안에 저장되어 있는 게시글 정보를 전부 화면에 출력 -->
					<c:forEach items="${list}" var="b">
						<!-- 반복문 forEach 사용, list에 해당하는 데이터 하나씩 꺼냄-->
						<!-- b라는 공간에 저장됨.  -->
					<tr>
						<td>${b.idx}</td>
						<!-- 1. QueryString으로 데이터 보내기
							 2. 경로상에 그냥 바로 데이터 포함해서 보내기
						-->
						<td><a href="${cpath}/boardContent/${b.idx}">${b.title}</a></td>
						<td>${b.writer}</td>
						<td>${b.indate}</td>
					</tr>
					</c:forEach>
				</table>	
				<button class="btn btn-border" onclick="location.href='${cpath}/register'">글쓰기</button>		
			</div>	
			</div>
		</div>
	
	
	
</body>
</html>