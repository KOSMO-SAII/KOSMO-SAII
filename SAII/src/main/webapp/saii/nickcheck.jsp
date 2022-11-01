<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	
	}
	function nicksend(){
		opener.document.getElementById("usernickname").value = document.getElementById("nicknamechk").value;
	}

</script>
</head>

<body>
	<h2>닉네임 중복확인</h2>
	<form name="writeFrm" method="post" 
   		action="http://localhost:8081/SAII/nickcheck" onsubmit="return nickCk(this);">
	<table border="1" width="50%">
         <tr>
            <td>NICKNAME</td>
            <td>
               <input type="text" id="nicknamechk" name="nickname" style="width:150px;" value="${param.nickname }"/>
               <button type="submit" onclick="nicksend()">검증하기</button>
            </td>
         </tr>    
      </table>
 	</form>
</body>
</html>