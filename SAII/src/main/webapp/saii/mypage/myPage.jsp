<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script>
function passCk(){     
		
	var popupWidth = 650;
	var popupHeight = 250;
	
	var popupX = (window.screen.width / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	
	var popupY= (window.screen.height / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	
		window.open("http://localhost:8081/SAII/saii/mypage/passcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				'width=' + popupWidth + ', height=' + popupHeight + ', resizable=no, scrollbars=no,top=' + popupY + ',left=' + popupX);
	}
function divshow1(){
	document.getElementById("show1").style.display="block";
	document.getElementById("show2").style.display="none";
	document.getElementById("show3").style.display="none";
	
}
function divshow2(){
	document.getElementById("show1").style.display="none";
	document.getElementById("show2").style.display="block";
	document.getElementById("show3").style.display="none";
	
}
function divshow3(){
	document.getElementById("show1").style.display="none";
	document.getElementById("show2").style.display="none";
	document.getElementById("show3").style.display="block";
	
}
function imgcg(input){
	if(input.files && input.files[0]){
		
		const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = function(e) {
            document.getElementById("preview").src=e.target.result;
            document.getElementById("preview1").src=e.target.result;
        };
     	// reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0]);
	}else{
		document.getElementById("preview").src="/SAII/Storage/${dto.n_profile_img}";
	}
}
function withdrawcheck(){
	var memberdel = confirm('정말로 탈퇴하시겠습니까?');
	
	if(memberdel){
		alert('탈퇴됨');
		return true;
	}
	else{
		alert('탈퇴 안했네?');
		return false;
	}
}

function imgoriginal(){
	   document.getElementById("preview1").style.display="block";
	}
function imgoriginalhidden(){
   document.getElementById("preview1").style.display="none";
}


</script>
<style>
#preview{
   transition-timing-function: ease-in;
}
#preview1{
   display: none;
   z-index: 1;
   position: absolute;
   top: 7%;
   transition: 2s;
   transition-timing-function: ease-in;
   max-height: 500px;
   max-width: 500px;
}

#show2{
	display: none;
	width: 1000px;
	position: relative;
	margin: auto;
}
#show3{
	display: none;
}

tr>td:first-child {
	text-align: right
}

h3 {
	text-align: center;
}
h2 {
	text-align: center;
	position: relative;
}

#edit {
	position: relative;
	font-size: 15px;
	margin-top: 24px; 
	height: 38px; 
	width: 120px; 
	left: 283px;
	background: none; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    cursor: pointer;
    border-radius: 4px; 
    background-color: #98dde3;
	
}

#profile{
	position: absolute;
	width: 300px;
	height: 300px;
	top: 100px;
	left: -250px;
}
#filebutton {
	display: none;
}

.input_file_button{
	position:relative;
	top:10px;
	cursor: pointer;
	display:inline-block;
	font-size: 15px;
	margin-top: 24px; 
	height: 30px; 
	width: 120px; 
	background: none; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    cursor: pointer;
    border-radius: 4px; 
    background-color: #98dde3;
    padding-top: 8px;
}
#sub{
	display: none;
}
.input_submit{
	position:relative;
	top:14px;
	cursor: pointer;
	display:inline-block;
	font-size: 15px;
	margin-top: 24px; 
	height: 30px; 
	width: 120px; 
	background: none; 
	border: none;
    color: #fff; 
    font-size: 16px; 
    font-weight: 500; 
    cursor: pointer;
    border-radius: 4px; 
    background-color: #98dde3;
    padding-top: 8px;
}
#mboard{
	position:relative;
	height: 300px;
	width: 818px;
	left: 50%;
	margin-left: -400px;
}

.div1 {
	text-align: center;
	vertical-align: middle;
}

.div1-1{
	width: 120px;
	height: 40px;
	margin: auto;
	display: inline-block;
	padding-top: 20px;
	text-align: center;
	align-items: center;
	background-color: rgb(236,236,236);
	border-radius: 10px;
}
#del {
	display: none;
}
.withdraw{
	background-color: black;
	color: white;
	cursor: pointer;
	padding: 6px 25px;
	border-radius: 5px;
	display:inline-block;
	width: 100px
}

.mylabel{
	vertical-align: 5px; 
	color: #999;
}
.mydiv{
	padding: 0px 0px 8px 0px;
}
.myinput{
	vertical-align: 5px; 
	width:400px; 
	height:40px; 
	display: inline-block;
	border:none; 
	background-color: #F7F7F7;
}
#show1 {
	position:relative;
	width: 400px; 
	margin:auto; 
	padding:30px
}
.mylist{
	position: relative;
	margin: auto;
	width: 500px;
	height: 150px;
	box-shadow: rgb(31 38 135 / 20%) 0px 8px 32px 0px;
	outline: 5px;
	margin-bottom: 10px;
	border-radius: 4px;
	top: 22px;
	
}
.mylist:hover{
	transform: scale(1.1) ;
    border: 10px;
    border-color: gray;
    background-color: #FFF4F9;
}
.imgdiv {
	position: relative;
	left: 316px;
	top: 15px;
}
.spannick {
	position: relative;
	top: -40px;
}
.divdate{
	position: absolute;
	bottom: 2%;
	right: 3%;
}
span{
	color: #5075B5;
	font-weight: bold;
}

.divtitle{
	position: absolute;
	top: 10%;
	left: 4%
}

.divregion{
	position: absolute;
	top: 10%;
	left: 58%;
}

.divname{
	position: absolute;
	top: 33%;
	left: 4%;
}

.divcount{
	position: absolute;
	top: 56%;
	left: 4%;
	
}
a {
	color: black;
}
.favoimg{
	position: relative;
	width:70px;
	height:70px;
 	margin: auto;
 	top: 32px;
	
}
.mylist2{
	position: relative;
	margin: auto;
	width: 500px;
	height: 150px;
	box-shadow: rgb(31 38 135 / 20%) 0px 8px 32px 0px;
	outline: 5px;
	margin-bottom: 10px;
	border-radius: 4px;
	top:-70px;
	
}
.myfavo{
	height: 160px;
	width: 900px;
	position: relative;
	margin: auto;
}
.mylist2:hover{
	transform: scale(1.1) ;
    border: 10px;
    border-color: gray;
    background-color: #FFF4F9;
}
.spanimg{
	position:relative;

	top: 49px;
}
.favodiv{
	position: relative;
	margin: auto;
	height: 150px;
	width: 240px;
	right: 355px;
	top: 78px;
	text-align: center;
}
</style>
</head>
<body>
<%@ include file="../top.jsp" %>
<h3>마이페이지</h3>
<div class="div1">
	<div class="div1-1" onclick="divshow1()">내 정보</div>
	<div class="div1-1" onclick="divshow2()">내가 만든 코스</div>
	<div class="div1-1" onclick="divshow3()">내가 찜한 코스</div>
</div>
<div id="show1" >
	<form method="get" onsubmit="passCk()">
	<input id="edit" type="submit" value="수정하러가기" >
	<div class="mydiv">
	<label class="mylabel">아이디</label><br/>
	<input class="myinput" type="text" id="userId" name="id" value="${dto.id}" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">비밀번호</label><br/>
	<input class="myinput" type="password" name="pw" value="${dto.pw }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">닉네임</label><br/>
	<input class="myinput" type="text" name="nickname" value="${dto.nickname }"readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">이름</label><br/>
	<input class="myinput" type="text" name="name"	value="${dto.name }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">생년월일</label><br/>
	<input class="myinput" type="Date" name="birthday" value="${dto.birthday }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">성별</label><br/>
	<input class="myinput" type="text" name="sex" value="${dto.sex }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">전화</label><br/>
	<input class="myinput" type="text" name="phone" value="${dto.phone }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">이메일</label><br/>
	<input class="myinput" type="email" name="email" value="${dto.email }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">주소</label><br/>
	<input class="myinput" type="text" name="address" value="${dto.address }"readonly>
	</div>
	</form>
	
	</table>
	<div id="profile" align="center">
	<img id="preview" src="/SAII/Storage/${dto.n_profile_img}" width="120px" height="120px" style="border-radius: 50px" onclick="imgoriginal()">
	<form action="http://localhost:8081/SAII/upload?id=${dto.id }" name="img" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${dto.id}">
		<label class="input_file_button" for="filebutton">사진선택</label>
		<input id="filebutton" type="file" name="o_profile_img" onchange="imgcg(this)" required><br>
		<label class="input_submit" for="sub">프로필 변경</label>
		<input id="sub" type="submit" value="프로필변경">
	</form>
	</div>
</div>
<img id="preview1" src="/SAII/Storage/${dto.n_profile_img}" onclick="imgoriginalhidden()">
<div id="show2">
	<div class="imgdiv">
		<img src="/SAII/Storage/${dto.n_profile_img}" width="90px" height="90px" style="border-radius: 50px">
		<span class="spannick">${dto.nickname}님의 코스 갯수 ${map.mylistcount }</span>
	</div>
	<c:choose>
		<c:when test="${not empty list }">
			<c:forEach items="${list}" var="mlist">
				<div class="mylist">
					<a href="http://localhost:8081/SAII/view?m_id=${miist.m_id }">
						<div class="divtitle"><span>코스이름 </span> ${mlist.m_title }</div>
						<div class="divregion"><span>코스지역 </span> ${mlist.region}</div>
						<div class="divname"><span>코스경로 </span> ${mlist.pname}</div>
						<div class="divdate"><span>게시날짜 </span> ${mlist.m_postdate}</div>
						<div class="divcount"><span>선택장소 </span> ${mlist.count}</div>
					</a>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>등록된 게시물이 없어요~</div>
		</c:otherwise>
	</c:choose>
</div>

<div id="show3">
	<c:choose>
		<c:when test="${not empty flist }">
			<c:forEach items="${flist}" var="flist2">
			<div class="myfavo">
				<div class="favodiv">
					<img class="favoimg"src="/SAII/Storage/${flist2.profile}"  style="border-radius: 50px">
					<br><span class="spanimg">${flist2.nickname }의 게시물</span>
				</div>
				<div class="mylist2">
					<a href="http://localhost:8081/SAII/view?m_id=${flist2.m_id }">
						<div class="divtitle"><span>코스이름 </span> ${flist2.m_title }</div>
						<div class="divregion"><span>코스지역 </span> ${flist2.region}</div>
						<div class="divname"><span>코스경로 </span> ${flist2.pname}</div>
						<div class="divdate"><span>게시날짜 </span> ${flist2.m_postdate}</div>
						<div class="divcount"><span>선택장소 </span> ${flist2.count}</div>
					</a>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>좋아요 누른 코스가 없어요~</div>
		</c:otherwise>
	</c:choose>
</div>
<div>
	<form action="http://localhost:8081/SAII/withdraw" onsubmit="return withdrawcheck()">
		<input type="submit" id="del" >
		<label class="withdraw" for="del" >회원탈퇴</label>	
	</form>
</div>
<a href="http://localhost:8081/SAII/home">돌아가기</a>


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>