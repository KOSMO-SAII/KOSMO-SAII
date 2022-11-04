<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복확인 창</title>
<style>
#form {width: 400px; margin:auto; padding:30px}
h2 { text-align: center;}

div.ck01 {display: flex; margin:0 auto; top:50%; left:50%;}
.button {padding: 0; border: none; border-radius:5px;  font: inherit;
  			color: inherit; background-color: #FFE3ED; cursor: pointer;}
input[type=text]  { vertical-align: 5px; width:400px; height:40px; display: inline-block;
									border:none; background: #F7F7F7;}
.ckk {margin:0; border:none; overflow:visible; border-radius:0; display:inline-block;
				vertical-align: middle; font-size: 0.8rem; line-height: 38px; text-align: center;
    			text-decoration: none; text-transform: uppercase; transition: 0.1s; 
    			transition-property: color, background-color, border-color;
    			width: 60px !important; }	
</style>

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

   
   <form id="form" name="writeFrm" method="post" "
   		action="http://localhost:8081/SAII/idcheck" onsubmit="return idCk(this);">
      
         <div class="ck01">
               <input type="text" id="Idchk" name="id"  value="${param.id }"/>
               <button type="submit" class="ckk" onclick="idsend()">검증</button>
        </div>    
      
   </form>
   
	
	
	
<!--마우스 커서-->	
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>