<%@page import="saii.domain.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
</head>
<body>
  <form name="idsearch" method="post">
	<c:choose>
		<c:when test="${!empty pw }" >
      	<div class = "container">
      	<div class = "found-success">
	      <h4>  회원님의 비밀번호는 </h4>  
	      <div class ="found-pw">${pw }</div>
	      <h4>  입니다 </h4>
	     </div>
	     <div class = "found-login">
 		    <input type="button" id="btnLogin" value="로그인" onClick = 'login()'/>
       	</div>
       </div>
       	</c:when>
 		<c:otherwise>
        <div class = "container">
      	<div class = "found-fail">
	      <h4>  등록된 정보가 없습니다 </h4>  
	     </div>
	     <div class = "found-login">
 		    <input type="button" id="btnback" value="다시 찾기" onClick="history.back()"/>
 		    <input type="button" id="btnjoin" value="회원가입" onClick="joinin()"/>
       	</div>
       </div>
       
	    <div class = "adcontainer">
          
		</div>  
		</c:otherwise> 

 	</c:choose>
      </form>
      </body>
</html>