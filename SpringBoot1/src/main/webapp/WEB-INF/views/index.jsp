<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>첫 번째 Spring boot 웹페이지</h1>
	<!-- 요청 url: /login -->
	<form action="${cpath}/login" method="post">
		<!-- id, pw로 데이터 보내주기 -->
		ID : <input type="text" name="id">
		<br>
		PW : <input type="text" name="pw">
		<br>
		<input type="submit" value="로그인">
	</form>
	<br>
	<a href ="${cpath}/join">회원가입 바로가기</a>	
	
	
</body>
</html>