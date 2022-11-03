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
      form.pw.focus();
      form.pw.select();
      return false;
   }

   //비민번호 유효성 검사
   for (var j = 0; j < form.pw.value.length; j++) {
      ch = form.pw.value.charAt(j)
      if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
         alert("비밀번호는 영문 대소문자, 숫자만 입력가능합니다.")
         form.pw.focus();
         form.pw.select();
         return false;
      }
   }
   //비밀번호 재확인
   if (!(form.pw.value == form.pw2.value)) {
      alert("비밀번호가 일치하지 않습니다.");
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

</script>
<style>
tr>td:first-child {
	text-align: right
}

body:first-child>table>input,[type=email],[type=Date],[type=text],[type=password] {
	width: 180px;
	height: 30px;
	border:0;
	font-size: 15px;
	border-radius: 10px;
	background-color: rgb(255, 249, 227);
	vertical-align: middle;
}
h4 {
	text-align: center;
}
#edittable {
	position:relative;
	top:40px;
	width: 315px;
	background-color: rgb(255, 243, 200);
	border-radius: 20px;
	box-shadow: 10px 10px 10px rgb(255,243,200),-10px -10px 10px rgb(255,243,200),-10px 10px 10px rgb(255,243,200),10px -10px 10px rgb(255,243,200);
}

#editsus{
	position:relative;
	top:30px;
	background-color: #FF6600;
	color: white;
	cursor: pointer;
	padding: 6px 25px;
	border-radius: 5px;
	display:inline-block;
	width: 100px;
	border: 0px;
}



</style>
</head>
<body>
<h4>회원정보 수정</h4>
<div>
<form action="http://localhost:8081/SAII/mypage.edit?id=${dto.id }" method="post" id="form" onsubmit="return validateform(this)">
<table align="center" width="315px">
	<tr>
		<td><input type="submit" value="수정완료" id="editsus" ></td>
	</tr>
</table>
<table width="50" align="center" name="edittable" id="edittable">
	<tr>
		<td>아&nbsp&nbsp&nbsp&nbsp이&nbsp&nbsp&nbsp&nbsp디 : </td>
		<td><input type="text" name="id" value="${dto.id}" readonly></td>
	</tr>
	<tr>
		<td>비&nbsp&nbsp밀&nbsp&nbsp번&nbsp&nbsp호 : </td>
		<td><input type="password" name="pw" value="${dto.pw }"> </td>
	</tr>
	<tr>
		<td>비밀번호 확인 : </td>
		<td><input type="password" name="pw2" value="${dto.pw }"></td>
	<tr>
	<tr>
		<td>닉&nbsp&nbsp&nbsp&nbsp네&nbsp&nbsp&nbsp&nbsp임 : </td>
		<td><input type="text" name="nickname" value="${dto.nickname }"></td>
	</tr>
	<tr>
		<td>이&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp름 : </td>
		<td><input type="text" name="name" value="${dto.name }" readonly></td>
	</tr>
	<tr>
		<td>생&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp일 : </td>
		<td><input type="text" name="birthday" value="${dto.birthday }"></td>
	</tr>
	<tr>
		<td>성&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp별 : </td>
		<td><input type="text" name="sex" value="${dto.sex }" readonly></td>
	</tr>
	<tr>
		<td>전&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp화 : </td>
		<td><input type="text" name="phone" value="${dto.phone }" ></td>
	</tr>
	<tr>
		<td>이&nbsp&nbsp&nbsp&nbsp메&nbsp&nbsp&nbsp&nbsp일 : </td>
		<td><input type="email" name="email" value="${dto.email }"></td>
	</tr>

	
	<tr><td>도로명 주소 : </td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="address" value="${dto.address }" /></td></tr>
	<tr>
		<td><input type="button" onClick="goPopup();" value="주소찾기"/></td>
	</tr>
</table>
</form>
</div>

</body>
</html>