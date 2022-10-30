<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="http://localhost:8081/chilsungsaii/CSS/LoginForm.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.UserId }">
	<script>
		function validateForm(form) {
			if(!form.user_id.value==" "{
				alert("아이디나 패스워드를 확인해주세요.");
				return false;
			}
			if(!form.user_pw.value==" s"){
				alert("아이디나 패스워드를 확인해주세요.");
				return false;
			}
	/* 		if(!form.user_id.value==""){
				alert("아이디를 입력하세요.");
				return false;
			}
			if(!form.user_pw.value==""){
				alert("패스워드를 입력하세요.");
				return false;
			} */
		}
	</script>
	<script>

	$(document).ready(function(){
	 
	    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var key = getCookie("key");
	    $("#userId").val(key); 
	     
	    if($("#userId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("key");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#userId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
	        }
	    });
	});
	 
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}
		
</script>
	<form action="http://localhost:8081/SAII/login" method="post" name="LoginFrm" onsubmit="return validateForm(this);">
		 아이디 : <input type="text" name="user_id" value="${param.user_id}" onclick="getCookie()"/><br/>
		패스워드 : <input type="password" name="user_pw" value="${param.user_pw}" /><br/>
		<input type="checkbox" id="idSaveCheck">아이디 기억하기
		<input type="submit" value="로그인" value="${param.user_id}"><br/>
		<a href="http://localhost:8081/chilsungsaii/IdPwCheck/findId2.jsp">[아이디 찾기]</a>
	  	<a href=" ">[비밀번호 찾기]</a>   
	</form>
	</c:when>
	<c:otherwise>
		${sessionScope.UserName}회원님, 로그인 하셨습니다
		<a href="http://localhost:8081/SAII/home">[돌아가기]</a>
		<a href="http://localhost:8081/SAII/logout">[로그아웃]</a>
	</c:otherwise>
		
</c:choose>
		
</body>
</html>