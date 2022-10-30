<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	}
	function idsend(){
		opener.document.getElementById("userId").value = document.getElementById("Idchk").value;
	}
</script>
</head>

<body>
	<h2>ID 중복확인</h2>
   <form name="writeFrm" method="post" 
   		action="../idcheck" onsubmit="return idCk(this);">
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