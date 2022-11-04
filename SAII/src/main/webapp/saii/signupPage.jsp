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
	input[type=text], input[type=password] { vertical-align: 5px; width:400px; height:40px; display: inline-block;
									border:none; background: #F7F7F7;}
	
	label { vertical-align: 5px; color: #999;}
	a {vertical-align: 5px;}
	select {vertical-align: 6px; width: 80px; height:30px;}
	
	.button {padding: 0; border: none; border-radius:5px;  font: inherit;
  			color: inherit; background-color: #EDEDED; cursor: pointer;}
	
	.ck-button {margin:0; border:none; overflow:visible; border-radius:0; display:inline-block;
				vertical-align: middle; font-size: 0.8rem; line-height: 38px; text-align: center;
    			text-decoration: none; text-transform: uppercase; transition: 0.1s; 
    			transition-property: color, background-color, border-color;
    			background-color:#EDEDED;
    			width: 60px !important; }	
    			
    div {padding: 0px 0px 8px 0px;}			
    			
    div.ckck {display: flex;}
    
    input.sign {margin-top: 24px; height: 45px; width: 100%; background: none; border: none;
    		color: #fff; font-size: 16px; font-weight: 500; cursor: pointer;
    		border-radius: 4px; background-color: #98dde3;}
    		
    
	
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
	        $('#year').append('<option value="' + i + '">' + i + '</option> ');    
	    }

	    // 월별 selectbox 만들기            
	    for(var i=1; i <= 12; i++) {
	        var mm = i > 9 ? i : "0"+i ;            
	        $('#month').append('<option value="' + mm + '">' + mm + '</option> ');    
	    }
	    
	    // 일별 selectbox 만들기
	    for(var i=1; i <= 31; i++) {
	        var dd = i > 9 ? i : "0"+i ;            
	        $('#day').append('<option value="' + dd + '">' + dd+ '</option> ');    
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
		//닉네임 길이 체크 (6~15자)
		if (form.nickname.value.length < 2 || form.nickname.value.length > 15) {
			alert("닉네임을 2~15자까지 입력해주세요.")
			form.nickname.focus();
			form.nickname.select();
			return false;
		}
		
		var popupWidth = 650;
		var popupHeight = 250;

		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

		window.open("http://localhost:8081/SAII/saii/nickcheck.jsp?nickname="+document.getElementById("usernickname").value, "chkForm", 
				'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);

	}
	
	function idCk(){
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
		
		var popupWidth = 650;
		var popupHeight = 250;

		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	window.open("http://localhost:8081/SAII/saii/idcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
			'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
	    



		 
	}
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
</head>
<body>
	<h2>회원가입</h2>
	<form id="form" method="post" action="http://localhost:8081/SAII/signup" enctype="multipart/form-data" onsubmit="return check();">
	
	<div>
	<label>아이디 </label> 
	<div class="ckck">
	<input type="text" id="userId" name="id"  placeholder="영문,숫자로 6~15자" value="${param.id}" />
	<button type="button" class="ck-button"  onclick="idCk(); ">확인</button> <br/>
	<!--  <input type="button" class="button" value="중복체크" onclick="idCk(); "/> <br/>-->
	</div>
	</div>
	
	<div>
	<label>비밀번호</label>
	<input type="password" name="pw" placeholder="영문,숫자로 8~15자" value="${param.pw}" ><br/>
	</div>
	
	<div>
	<label>비밀번호 재확인</label>
	<input type="password" name="pw2" value="${param.pw2}"/><br/>
	</div>
    
    <div>
    <label>닉네임</label>  
    <div class="ckck">
	<input type="text" id="usernickname" name="nickname" placeholder="입력하세요" value="${param.nickname}"/>
	<button type="button" class="ck-button"  onclick="nickCk();">확인</button> <br/>
	<!--  <input type="button" value="중복체크" onclick="nickCk();"/> <br/> <br/>-->
	</div>
	</div>
    
    <div>
    <label>이름</label>  <br/>
    <input type="text" name="name" placeholder="한글만 입력 가능합니다." value="${param.name1}"/><br/>
    </div>
    
    <div>
    <label>성별</label>  <br/>
    <input type="radio" name="sex" value="여자" checked/><a>여자</a>
    <input type="radio" name="sex" value="남자" /><a>남자</a><br/> 
    </div>
    
    <div>
    <label>생년월일</label> <br/>
    <select name="yy" id="year" ></select><a>년</a>
	<select name="mm" id="month" ></select><a>월</a>
	<select name="dd" id="day" ></select><a>일</a><br/>
	</div>
    
    <div>
    <label>프로필</label> <br/>
    <input type="file" name="pick" /><br/>
    </div>
    
    <div>
    <label>주소</label> 
    <input type="text" id="roadFullAddr" name="address" value="${param.address}" onClick="goPopup();"> <br/>
    </div>
    
    <div>
    <label>이메일</label> 
    <input type="text" name="email" value="${param.email}"><br/>
    </div>
    
    <div>
    <label>핸드폰번호</label>
    <input type="text" name="phone" value="${param.phone}"><br/>
    </div>
    
    <div>
    <div >
    <input class="sign" type="submit"  value="회원가입하기" />
    </div>
    </div>
    
    </form>
    
<script src="http://localhost:8081/SAII/saii/JS/memAct.js"></script>


<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>