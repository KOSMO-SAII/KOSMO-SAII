<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
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

</script>

</head>
<body>
<h4>회원정보 수정</h4>
<div>
<form action="http://localhost:8081/SAII/mypage.edit?id=${dto.id }" method="post" id="form" onsubmit="return validateform(this)">
<input type="submit" value="회원정보 수정" ><br>
	아이디 : <input type="text" name="id" value="${dto.id}" readonly> <br/>
	비밀번호 : <input type="password" name="pw" value="${dto.pw }"> <br/>
	비밀번호 확인 : <input type="password" name="pw2" value="${dto.pw }"><br/>
	닉네임 : <input type="text" name="nickname" value="${dto.nickname }"><br/>
	이름 : <input type="text" name="name" value="${dto.name }" readonly><br/>
	생일 : <input type="text" name="birthday" value="${dto.birthday }"><br/>
	성별 : <input type="text" name="sex" value="${dto.sex }" readonly><br/>
	전화 : <input type="text" name="phone" value="${dto.phone }" ><br/>
	이메일 : <input type="email" name="email" value="${dto.email }"><br/>
	주소 : <input type="text" name= "address" value="${dto.address }">
</form>
</div>

</body>
</html>