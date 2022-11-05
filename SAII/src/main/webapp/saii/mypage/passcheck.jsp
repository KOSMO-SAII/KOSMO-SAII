<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function passCk(form){           
		if(form.password.value == ""){
			alert("패스워드를 입력하세요");
			form.nickname.focus();
			return false;
		}
	}	

</script>
<style>
h2{
	text-align: center;
}
label{
	vertical-align: 5px; 
	color: #999;
}
input {
	vertical-align: 5px; 
	width:300px; 
	height:40px; 
	display: inline-block;
	border:none; 
	background-color: #F7F7F7;
}
div {
	position:relative;
	width: 400px; 
	margin:auto; 
	padding:30px
}
#ch{
	position:relative;
	cursor: pointer;
	display:inline-block;
	font-size: 15px;
	margin-top: 24px; 
	height: 34px; 
	width: 60px; 
	background: none; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    cursor: pointer;
    border-radius: 4px; 
    background-color: #98dde3;
    padding-top: 8px;
    text-align: center;
    left: -5px;
}
#btn{
	display: none;
}
</style>
</head>
<body>
<h2>본인확인</h2>
<div>
	<form name="writeFrm" method="post" action="http://localhost:8081/SAII/passcheck" onsubmit="return passCk(this);">
	<label>비밀번호를 입력해주세요</label><br/>
	<input type="hidden" name="id" value="${UserId }" />
    <input type="password" id="passwordchk" name="password" "/>
    <button id="btn" type="submit" onclick="nicksend()">검증하기</button>
    <label id="ch" for="btn">확인</label>
 	</form>
 </div>

<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>