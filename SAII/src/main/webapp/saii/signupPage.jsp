<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 입력</title>
<style>
	#form {width: 400px; margin:auto; padding:30px}
	h2 { text-align: center; margin: 30px 0 30px 0;}
	input { vertical-align: 5px;}
	label { vertical-align: 5px;}
	a {vertical-align: 5px;}
	select {vertical-align: 5px;}
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
		nickWin = window.open("nickcheck.jsp?nickname="+document.getElementById("usernickname").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");
	}
	
	function idCk(){
		if(form.id.value == ""){
			alert("아이디를 입력하세요");
			form.id.focus();
			return false;
		}
		window.name = "parentForm";            
		idWin = window.open("idcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");

	}
	
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form id="form" method="post" action="../signup" enctype="multipart/form-data" onsubmit="return check();">
	<label>아이디 </label> 
	<input type="text" id="userId" name="id"  placeholder="영문,숫자로 6~15자" value="${param.id}" />
	<input type="button" value="중복체크" onclick="idCk(); "/> <br/>
	
	<label>비밀번호</label>
	<input type="password" name="pw" placeholder="영문,숫자로 8~15자" value="${param.pw}" ><br/>
	
	<label>비밀번호 재확인</label>
	<input type="password" name="pw2" value="${param.pw2}"/><br/>
    
    <label>닉네임</label>  
    <input type="text" id="usernickname" name="nickname" placeholder="입력하세요" value="${param.nickname}"/>
    <input type="button" value="중복체크" onclick="nickCk();"/> <br/>
    
    <label>이름</label>  
    <input type="text" name="name" placeholder="한글만 입력 가능합니다." value="${param.name1}"/><br/>
    
    <label>성별</label>  
    <input type="radio" name="sex" value="여자" checked/><a>여자</a>
    <input type="radio" name="sex" value="남자" /><a>남자</a><br/> 
    
    <label>생년월일</label> 
    <select name="yy" id="year" ></select><a>년</a>
	<select name="mm" id="month" ></select><a>월</a>
	<select name="dd" id="day" ></select><a>일</a><br/>
    
    <label>프로필</label> 
    <input type="file" name="pick" /><br/>
    
    <label>주소</label> 
    <input type="text" name="address" value="${param.address}"> <br/>
    
    <label>이메일</label> 
    <input type="text" name="email" value="${param.email}"><br/>
    
    <label>핸드폰번호</label>
    <input type="text"><br/>
    
    <input type="submit"  value="회원가입하기" />
    </form>
    
<script src="memAct.js"></script>
</body>
</html>