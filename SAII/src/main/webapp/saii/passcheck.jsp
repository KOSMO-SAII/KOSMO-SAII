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
@font-face {
    font-family: 'SF_IceMango';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/SF_IceMango.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body{
	font-family: 'SF_IceMango';
}

h2 {
	text-align: center;
}
#table1 {
	text-align: center;
	vertical-align: middle;
	background-color: rgb(255, 243, 200);
	border-radius: 20px;
	box-shadow: 10px 10px 10px rgb(255,243,200),-10px -10px 10px rgb(255,243,200),-10px 10px 10px rgb(255,243,200),10px -10px 10px rgb(255,243,200);
	font-size: x-large;
}
input{
	position:relative;
	width: 180px;
	height: 30px;
	border:0;
	font-size: 15px;
	border-radius: 10px;
	background-color: white;
	vertical-align: middle;
	box-shadow: 10px 10px 10px white,-10px -10px 10px white,-10px 10px 10px white,10px -10px 10px white;
	top: -5px;
	left: -10px;
}
button {
	display: none;
}
label{
	background-color: #FF6600;
	color: white;
	cursor: pointer;
	padding: 6px 25px;
	border-radius: 5px;
	display:inline-block;
	width: 50px
}

</style>
</head>
<body>
<h2>본인확인</h2>
	<form name="writeFrm" method="post" action="http://localhost:8081/SAII/passcheck" onsubmit="return passCk(this);">
	<table id="table1" width="600px" align="center">
         <tr>
            <td>비밀번호를 입력해주세요.</td>
            <td>
            	<input type="hidden" name="id" value="${UserId }" />
               <input type="password" id="passwordchk" name="password" style="width:150px;"/>
               <button id="btn" type="submit" onclick="nicksend()">검증하기</button>
               <label for="btn">확인</label>
            </td>
         </tr>    
      </table>
 	</form>

<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>