<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
function validateform(form){
   if(!form.pw.value){
      alert("비밀번호를 입력하세요.")
      return false;
   }
   if(!form.nickname.value){
      alert("닉네임을 입력하세요.")
      return false;
   }
   if(!form.birthday.value){
      alert("생년월일을 입력하세요.")
      return false;
   }
   if(!form.phone.value){
      alert("전화번호를 입력하세요.")
      return false;
   }
   if(!form.email.value){
      alert("이메일을 입력하세요.")
      return false;
   }
   if(!form.address.value){
      alert("주소를 입력하세요.")
      return false;
   }
   //비밀번호 길이 체크(8~15자 까지 허용)
   if (form.pw.value.length < 8 || form.pw.value.length > 15) {
      alert("비밀번호를 8~15자까지 입력해주세요.")
			setTimeout(function(){ 
			document.form.pw.focus();
			document.form.pw.select();
			}, 10);
      return false;
   }

   //비민번호 유효성 검사
   for (var j = 0; j < form.pw.value.length; j++) {
      ch = form.pw.value.charAt(j)
      if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
         alert("비밀번호는 영문 대소문자, 숫자만 입력가능합니다.")
			setTimeout(function(){ 
			document.form.pw.focus();
			document.form.pw.select();
			}, 10);
         return false;
      }
   }
   //비밀번호 재확인
   if (!(form.pw.value == form.pw2.value)) {
      alert("비밀번호가 일치하지 않습니다.");
		setTimeout(function(){ 
			document.form.pw2.focus();
			document.form.pw2.select();
			}, 10);
      return false;
   }


   //이름을 한글로만 받기
   for (var k = 0; k < form.name1.value.length; k++) {
      ck = form.name1.value.charAt(k);
      if (((ck < "ㅏ") || (ck > "히")) && ((ck < "ㄱ") || (ck > "ㅎ"))) {
         alert("한글만 입력 가능합니다.");
         form.name1.focus();
         form.name1.select();
         return false;
      }
   }


   //이름 2글자 이상
   if (form.name1.value.length < 2) {
      alert("이름을 2자 이상 입력해주십시오.");
      form.name1.focus();
      form.name1.select();
      return false;
   }
}
//opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색RL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("http://localhost:8081/SAII/saii/juso.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
  //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.getElementById("roadFullAddr").value = roadFullAddr;

		
}

function passcheck(){
   
	if (form.pw.value.length < 8 || form.pw.value.length > 15) {
	      alert("비밀번호를 8~15자까지 입력해주세요.")
			setTimeout(function(){ 
			document.form.pw.focus();
			document.form.pw.select();
			}, 10);
	      return false;
	   }

   //비민번호 유효성 검사
   for (var j = 0; j < form.pw.value.length; j++) {
      ch = form.pw.value.charAt(j)
      if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
         alert("비밀번호는 영문 대소문자, 숫자만 입력가능합니다.")
			setTimeout(function(){ 
			document.form.pw.focus();
			document.form.pw.select();
			}, 10);
         return false;
      }
   }
   document.getElementById("passhidden").style.display="none";
}

function passoverlap(){
	   if (!(form.pw.value == form.pw2.value)) {
		      alert("비밀번호가 일치하지 않습니다.");
				setTimeout(function(){ 
					document.form.pw2.focus();
					document.form.pw2.select();
					}, 10);
		      return false;
		   }
	
}
function pshd(){
	document.getElementById("passhidden").style.display="block";
}


</script>
<style>
.mylabel{
	vertical-align: 5px; 
	color: #999;
}
.mydiv{
	padding: 0px 0px 8px 0px;
}
.myinput{
	vertical-align: 5px; 
	width:400px; 
	height:40px; 
	display: inline-block;
	border:none; 
	background-color: #F7F7F7;
}
#show11 {
	position:relative;
	width: 400px; 
	margin:auto; 
	padding:30px
}
#editsus{
	position: relative;
	font-size: 15px;
	margin-top: 24px; 
	height: 30px; 
	width: 120px; 
	left: 283px;
	background: none; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    cursor: pointer;
    border-radius: 4px; 
    background-color: #98dde3;
}
h4{
	text-align: center;
}
.focus:focus{
	outline: 0;
}
#passhidden{
	display: none;
	position: relative;
	top: -610px;
	left: 430px;
}

</style>
</head>
<body>
<h4>회원정보 수정</h4>
<div id="show11">
<form action="http://localhost:8081/SAII/mypage.edit?id=${dto.id }" method="post" id="form" onsubmit="return validateform(this)">
	<input type="submit" value="수정완료" id="editsus" >
	<div class="mydiv">
	<label class="mylabel">아이디</label><br/>
	<input class="myinput focus" type="text" id="userId" name="id" value="${dto.id}" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">비밀번호 *</label><br/>
	<input class="myinput" type="password" name="pw" value="${dto.pw }" onblur="passcheck()" onclick="pshd()">
	</div>
	<div class="mydiv">
	<label class="mylabel">비밀번호 확인 *</label><br/>
	<input class="myinput" type="password" name="pw2" value="${dto.pw }" onblur="passoverlap()">
	</div>
	<div class="mydiv">
	<label class="mylabel">닉네임 *</label><br/>
	<input class="myinput" type="text" name="nickname" value="${dto.nickname }">
	</div>
	<div class="mydiv">
	<label class="mylabel">이름</label><br/>
	<input class="myinput focus" type="text" name="name"	value="${dto.name }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">생년월일</label><br/>
	<input class="myinput focus" type="Date" name="birthday" value="${dto.birthday }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">성별</label><br/>
	<input class="myinput focus" type="text" name="sex" value="${dto.sex }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">전화 *</label><br/>
	<input class="myinput" type="text" name="phone" value="${dto.phone }" >
	</div>
	<div class="mydiv">
	<label class="mylabel">이메일 *</label><br/>
	<input class="myinput" type="email" name="email" value="${dto.email }" >
	</div>
	<div class="mydiv">
	<label class="mylabel">주소 *</label><br/>
	<input class="myinput" type="text" id="roadFullAddr"  name="address" value="${dto.address }" onClick="goPopup();" />
	</div>
	<div id="passhidden">비밀번호는 8~15사이로 입력해주세요</div>
</form>
</div>
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>