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
.top_btn{
	cursor: pointer;
	font-size: 15px;
	margin-top: 24px; 
	height: 30px; 
	width: 120px; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    border-radius: 4px; 
    background-color: #98dde3;
    padding: 8px;
    text-decoration: none;
}
</style>
</head>
<body>
<img id=himg src="http://localhost:8081/SAII/saii/img/saii.png" width=60px; height=40px; onclick='location.href="http://localhost:8081/SAII/home"';>
<div id="abc">
	<a class="top_btn" href="javascript:window.history.back();">뒤로가기</a>
	<a  class="top_btn" href="http://localhost:8081/SAII/mainboard?page=1">전체게시판</a>
	<a  class="top_btn" href="http://localhost:8081/SAII/review_list">리뷰게시판</a>
	
	<c:if test="${UserId!=null}"><a class="top_btn" href="http://localhost:8081/SAII/mypage?id=${UserId }">마이 페이지</a></c:if>
	<c:if test="${UserId==null}"><a class="top_btn" href="http://localhost:8081/SAII/login">로그인</a></c:if>
	<c:if test="${UserId!=null}"><a class="top_btn" href="http://localhost:8081/SAII/logout">로그아웃</a></c:if>
	
</div>


<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>