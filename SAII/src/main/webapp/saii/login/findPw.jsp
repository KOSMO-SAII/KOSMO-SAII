<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 찾기</title>
<link rel="stylesheet" href="http://localhost:8081/SAII/saii/CSS/findIdPw.css">
</head>
<body>
<script>
function pw_search() { 
 	var frm = document.pwfindscreen;

 	if (frm.member_id.value.length < 1) {
	  alert("아이디를 입력해주세요");
	  return;
	 }

	 if (frm.member_phone.value.length != 11) {
		alert("핸드폰번호를 정확하게 입력해주세요");
		return;
	 }

 frm.method = "post";
 frm.action = "http://localhost:8081/SAII/findPw.do"; //controller 주소 
 frm.submit();  
 }
</script>
<div class = "container">
<form id="form" name="pwfindscreen" method = "POST">
			<div class = "search-title">
				<h3>패스워드 찾기</h3>
			</div>
		<section class = "form-search">
			<div class = "find-id">
				<label>아이디</label>
				<input type="text" name="member_id" class = "btn-id" placeholder = "회원 ID를 입력하세요">
			<br>
			</div>
			<div class = "find-phone">
				<label>휴대폰번호</label>
				<input type="text" onKeyup = "addHypen(this);" name="member_phone" class = "btn-phone" placeholder = "휴대폰번호를 '-'없이 입력하세요">
			</div>
			<br>
			
	</section>
	<div class ="btnSearch">
		<input class ="button" type="button" name="enter" value="찾기"  onClick="pw_search()">
		<input class ="button"  type="button" name="cancle" value="취소" onClick="history.back()">
 	</div>
 </form>
</div>
<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>