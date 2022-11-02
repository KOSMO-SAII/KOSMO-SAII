<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<a href="http://localhost:8081/SAII/home">홈으로</a>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/mypage?id=${UserId }">마이 페이지</a></c:if>
	<c:if test="${UserId==null}"><a href="http://localhost:8081/SAII/login">로그인</a></c:if>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/logout">로그아웃</a></c:if>
	
</body>
</html>