<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<style>
	#form {width: 400px; margin:auto; padding:30px}

</style>
<script>

	//생년월일 받기
	$(document).ready(function(){            
	    var now = new Date();
	    var year = now.getFullYear();
	    var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
	    var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());           
	    //년도 selectbox만들기               
	    for(var i = 1960 ; i <= year ; i++) {
	        $('#year').append('<option value="' + i + '">' + i + '년</option>');    
	    }

	    // 월별 selectbox 만들기            
	    for(var i=1; i <= 12; i++) {
	        var mm = i > 9 ? i : "0"+i ;            
	        $('#month').append('<option value="' + mm + '">' + mm + '월</option>');    
	    }
	    
	    // 일별 selectbox 만들기
	    for(var i=1; i <= 31; i++) {
	        var dd = i > 9 ? i : "0"+i ;            
	        $('#day').append('<option value="' + dd + '">' + dd+ '일</option>');    
	    }
	    $("#year  > option[value="+year+"]").attr("selected", "true");        
	    $("#month  > option[value="+mon+"]").attr("selected", "true");    
	    $("#day  > option[value="+day+"]").attr("selected", "true");       
	  
	})
	
	 
	function nickCk(){           
		if(form.nickname.value == ""){
			alert("닉네임을 입력하세요");
			form.nickname.focus();
			return false;
		}
		window.name = "parentForm";            
		nickWin = window.open("http://localhost:8081/SAII/saii/nickcheck.jsp?nickname="+document.getElementById("usernickname").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");
	}
	
	function idCk(){
		if(form.id.value == ""){
			alert("아이디를 입력하세요");
			form.id.focus();
			return false;
		}
		window.name = "parentForm";            
		idWin = window.open("http://localhost:8081/SAII/saii/idcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");

	}
	
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form id="form" method="post" action="http://localhost:8081/SAII/signup" enctype="multipart/form-data" onsubmit="return check();">
	아이디  <input type="text" id="userId" name="id"  placeholder="영문,숫자로 6~15자" value="${param.id}" />
			<input type="button" value="중복체크" onclick="idCk();"/> <br/>
    비밀번호  <input type="password" name="pw" placeholder="영문,숫자로 8~15자" value="${param.pw}"/><br/>
    비밀번호 재확인  <input type="password" name="pw2" value="${param.pw2}"/><br/>
    닉네임  <input type="text" id="usernickname" name="nickname" placeholder="입력하세요" value="${param.nickname}"/>
    		<input type="button" value="중복체크" onclick="nickCk();"/> <br/>
    이름  <input type="text" name="name" placeholder="한글만 입력 가능합니다." value="${param.name1}"/><br/>
    성별  <input type="radio" name="sex" value="여자" checked/>여자
    	<input type="radio" name="sex" value="남자" />남자<br/> 
    생년월일 
    <select name="yy" id="year" ></select>년
	<select name="mm" id="month" ></select>월
	<select name="dd" id="day" ></select>일<br/>
    프로필 <input type="file" name="pick" /><br/>
    주소 <input type="text" name="address" value="${param.address}"> <br/>
    이메일 <input type="text" name="email" value="${param.email}"><br/>
    <input type="submit" value="회원가입하기" />
    </form>
    

	<br/><br/>
	<a href="http://localhost:8081/SAII/login">돌아가기</a><br/>
	<a href="http://localhost:8081/SAII/home">시작 화면</a>
<script src="../JS/memAct.js"></script>
</body>
</html>