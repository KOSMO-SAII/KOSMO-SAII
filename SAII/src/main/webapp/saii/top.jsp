<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
#abc {
	position:absolute;
	display: inline-block;
	right: 2%;
	
}
</style>
</head>
<body>
<div id="abc">
	<a href="http://localhost:8081/SAII/home">홈으로</a>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/mypage?id=${UserId }">마이 페이지</a></c:if>
	<c:if test="${UserId==null}"><a href="http://localhost:8081/SAII/login">로그인</a></c:if>
	<c:if test="${UserId!=null}"><a href="http://localhost:8081/SAII/logout">로그아웃</a></c:if>
</div>


<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>