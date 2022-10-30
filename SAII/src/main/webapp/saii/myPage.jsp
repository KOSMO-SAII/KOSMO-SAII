<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script>
function passCk(){     
		window.name = "parentForm";            
		nickWin = window.open("http://localhost:8081/SAII/saii/passcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");
	}
</script>
</head>
<body>
	<h4>회원정보</h4>
	<div>
		<form method="get" onsubmit="passCk()">
			<input type="submit" value="회원정보 수정하기"><br> 
			아이디 : <input	type="text" id="userId" name="id" value="${dto.id}" readonly><br>
		</form>
		
			비밀번호 : <input type="password" name="pw" value="${dto.pw }" readonly><br>
			닉네임 : <input type="text" name="nickname" value="${dto.nickname }"readonly><br>
			이름 : <input type="text" name="name"	value="${dto.name }" readonly><br> 
			생일 : <input type="Date" name="birthday" value="${dto.birthday }" readonly><br>
			성별 : <input type="text" name="sex" value="${dto.sex }" readonly><br>
			전화 : <input type="text" name="phone" value="${dto.phone }" readonly><br>
			이메일 : <input type="email" name="email" value="${dto.email }" readonly><br>
			주소 : <input type="text" name="address" value="${dto.address }"	readonly>
			
			
		<form action="http://localhost:8081/SAII/upload?id=${dto.id }" name="img" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${dto.id}">
			<input type="file" name="o_profile_img" required>
			<input type="submit" value="프로필변경">
		</form>
		
	</div>
	<img src="/SAII/Storage/${dto.n_profile_img}" width="100px" height="100px" style="border-radius: 50px">

	<a href="http://localhost:8081/SAII/home">돌아가기</a>
</body>
</html>