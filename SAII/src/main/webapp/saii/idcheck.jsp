<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복확인 창</title>
<script>
	function idCk(form){
		if(form.id.value == ""){
			alert("아이디를 입력하세요");
			form.id.focus();
			return false;
		}
		//아이디 길이 체크 (6~15자)
		if (form.id.value.length < 6 || form.id.value.length > 15) {
			alert("아이디를 6~15자까지 입력해주세요.")
			form.id.focus();
			form.id.select();
			return false;
		}
		//아이디 유효성 검사 (영문소문자, 숫자만 허용)
		for (var i = 0; i < form.id.value.length; i++) {
			ch = form.id.value.charAt(i)
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
				alert("아이디는 영문 대소문자, 숫자만 입력가능합니다.")
				form.id.focus();
				form.id.select();
				return false;
			}
		}
		//아이디에 공백 사용하지 않기
		if (form.id.value.indexOf(" ") >= 0) {
			alert("아이디에 공백을 사용할 수 없습니다.")
			form.id.focus();
			form.id.select()
			return false;
		}
	}
	function idsend(){
		opener.document.getElementById("userId").value = document.getElementById("Idchk").value;
	}
</script>
</head>

<body>
	<h2>ID 중복확인</h2>
   <form name="writeFrm" method="post" 
   		action="http://localhost:8081/SAII/idcheck" onsubmit="return idCk(this);">
      <table border="1" width="90%">
         <tr>
            <td>ID</td>
            <td>
               <input type="text" id="Idchk" name="id" style="width:150px;" value="${param.id }"/>
               <button type="submit" onclick="idsend()">검증하기</button>
            </td>
         </tr>    
      </table>
   </form>
	
	
</body>
</html>