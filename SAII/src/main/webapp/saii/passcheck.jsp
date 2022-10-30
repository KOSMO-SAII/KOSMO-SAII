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
</head>
<body>
<h2>본인확인</h2>
	<form name="writeFrm" method="post" action="../passcheck" onsubmit="return passCk(this);">
	<table border="1" width="50%">
         <tr>
            <td>비밀번호를 입력해주세요.</td>
            <td>
            	<input type="hidden" name="id" value="${UserId }" />
               <input type="password" id="passwordchk" name="password" style="width:150px;"/>
               <button type="submit" onclick="nicksend()">검증하기</button>
            </td>
         </tr>    
      </table>
 	</form>
</body>
</html>