<%@page import="saii.domain.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="http://localhost:8081/SAII/saii/CSS/findIdPw.css">
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>아이디찾기</title>
<head>
</head>
<body>
  <form name="idsearch" method="post">
	<c:choose>
		<c:when test="${!empty requestScope.id }" >
      	<div class = "container">
      	<div class = "found-success">
	      <h4>  회원님의 아이디는 </h4>  
	      <div class ="found-id">${requestScope.id }</div>
	      <h4>  입니다 </h4>
	     </div>
	     <div class = "found-login">
 		    <input class="button" type="button" id="btnLogin" value="로그인"  onClick='location="loginPage.jsp"'/> <!--로그인 페이지로 이동-->
       	</div>
       </div>
       	</c:when>
 		<c:otherwise>
        <div class = "container">
      	<div class = "found-fail">
	      <h4>  등록된 정보가 없습니다 </h4>  
	     </div>
	     <div class = "found-login">
 		    <input class="button" type="button" id="btnback" value="다시 찾기" onClick='location="../saii/findId2.jsp"'/><!--아이디 찾기로 이동-->
 		    <input class="button"type="button" id="btnjoin" value="회원가입" onClick='location="../saii/signupPage.jsp"'/><!--회원 가입 페이지로 이동-->
       	</div>
       </div>
       
	    <div class = "adcontainer">
          
		</div>  
		</c:otherwise> 

 	</c:choose>
      </form>

<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>