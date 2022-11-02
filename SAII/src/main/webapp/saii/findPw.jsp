<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 찾기</title>
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
 frm.action = "http://localhost:8081/SAII//pwcotroll.do"; //controller 주소 
 frm.submit();  
 }
</script>
<form name="pwfindscreen" method = "POST">
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
		<input type="button" name="enter" value="찾기"  onClick="pw_search()">
		<input type="button" name="cancle" value="취소" onClick="history.back()">
 	</div>
 </form>

</body>
</html>