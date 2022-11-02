<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function id_search() { 
 	var frm = document.idfindscreen;

 	if (frm.member_name.value.length < 1) {
	  alert("이름을 입력해주세요");
	  return;
	 }

	 if (frm.member_phone.value.length != 11) {
		  alert("핸드폰번호를 정확하게 입력해주세요");
		  return;
	 }

 frm.method = "post";
 frm.action = "http://localhost:8081/SAII/findId.do"; //controller 주소 
 frm.submit();  
 }
</script>
<form name="idfindscreen" method = "POST">
			<div class = "search-title">
				<h3>아이디 찾기</h3>
			</div>
		<section class = "form-search">
			<div class = "find-name">
				<label>이름</label>
				<input type="text" name="member_name" class = "btn-name" placeholder = "등록한 이름">
			<br>
			</div>
			<div class = "find-phone">
				<label>번호</label>
				<input type="text" onKeyup = "addHypen(this);" name="member_phone" class = "btn-phone" placeholder = "휴대폰번호를 '-'없이 입력">
			</div>
			<br>
			
	</section>
	<div class ="btnSearch">
		<input type="button" name="enter" value="찾기"  onClick="id_search()">
		<input type="button" name="cancle" value="취소" onClick="history.back()">
 	</div>
 </form>

</body>
</html>