<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL(JSP Standard Tag Library) 설정, prefix="c" : 코어사용 선언, 기능들 - 변수 지원, 흐름 제어, URL 처리 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!-- == controller -->
<!-- c:set 저장영역에 객체 저장  -->
<!-- c:set var="변수명" value="값" -->
<!-- value="${pageContext.request.contextPath} : contextPath가 변경되더라도 수정 없이 사용할 수 있도록 기본경로 설정, 페이지가 바뀔 때 cpath 뒤에 추가로 적으면 됨.  -->
<!-- cpath 변수에 컨텍스트패스 경로를 동적으로 할당 / 동적 할당이란 프로그램이 실행되는 중인 런타임에 필요한 만큼의 메모리 공간을 확보하는 것-->
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
	  <h1>보유 도서 현황</h1>
	</div>
	
	<div class="container">
		<div class="card"> 
			<div class="card-header"></div>
			<div class="card-body">
				<table class="table table-bordered table-hover"> <!-- 띄어쓰기 두 번 : 클래스 명 두 개 -->
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작가</td>
						<td>출판사</td>
						<td>ISBN</td>
						<td>보유도서수</td>
					</tr>
					
					<!-- JSTL/El 사용해서 request 영역 안에 저장되어 있는 게시글 정보를 전부 화면에 출력 -->
					<c:forEach items="${list}" var="b">
					<tr>
						<td>${b.num}</td>
						<td>${b.title}</a></td>
						<td>${b.author}</td>
						<td>${b.company}</td>
						<td>${b.isbn}</td>
						<td>${b.count}</td>
					</tr>
					</c:forEach>
				</table>	
			</div>	
			</div>
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
		
	
	<script type="text/javascript">
		var cpath = "${cpath}";
	</script>
	<script src="resources/js/myfirstjs.js" ></script>
	
</body>
</html>