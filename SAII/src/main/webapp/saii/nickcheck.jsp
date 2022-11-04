<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#form {width: 400px; margin:auto; padding:30px}
h2 { text-align: center;}

div.ck02 {display: flex; margin:0 auto; top:50%; left:50%;}
.button {padding: 0; border: none; border-radius:5px;  font: inherit;
  			color: inherit; background-color: #FFE3ED; cursor: pointer;}
input[type=text]  { vertical-align: 5px; width:400px; height:40px; display: inline-block;
									border:none; background: #F7F7F7;}
.ckk {margin:0; border:none; overflow:visible; border-radius:0; display:inline-block;
				vertical-align: middle; font-size: 0.8rem; line-height: 38px; text-align: center;
    			text-decoration: none; text-transform: uppercase; transition: 0.1s; 
    			transition-property: color, background-color, border-color;
    			width: 60px !important; }	

    			
</style>
<script>
	function nickCk(form){           
		if(form.nickname.value == ""){
			alert("닉네임을 입력하세요");
			form.nickname.focus();
			return false;
		}
		//닉네임 길이 체크 (6~15자)
		if (form.nickname.value.length < 2 || form.nickname.value.length > 15) {
			alert("닉네임을 2~15자까지 입력해주세요.")
			form.nickname.focus();
			form.nickname.select();
			return false;
		}
	}
	
	
	
	function nicksend(){
		opener.document.getElementById("usernickname").value = document.getElementById("nicknamechk").value;
	}

</script>
</head>

<body>
	<h2>닉네임 중복확인</h2>
	<form id="form" name="writeFrm" method="post" 
   		action="http://localhost:8081/SAII/nickcheck" onsubmit="return nickCk(this);">
		
		<div class="ck02">
               <input type="text" id="nicknamechk" name="nickname" style="width:360px;" value="${param.nickname }"/>
               <button type="submit" class="ckk" onclick="nicksend()">검증</button>
        </div>
 	</form>
 	
<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>