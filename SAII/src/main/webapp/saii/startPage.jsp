<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초기화면</title>
</head>
<body>
	<h1>초기화면 입니다.</h1>
	
	<a href="http://localhost:8081/SAII/course_write">코스 작성 화면</a>
	<a href="http://localhost:8081/SAII/course_recommend">코스 추천 화면</a>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/mypage?id=${UserId }">마이 페이지</a></c:if>
	<c:if test="${UserId==null}"><a href="http://localhost:8081/SAII/login">로그인</a></c:if>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/logout">로그아웃</a></c:if>
	<a href="http://localhost:8081/SAII/signup">회원가입</a>
	
	
</body>
</html>