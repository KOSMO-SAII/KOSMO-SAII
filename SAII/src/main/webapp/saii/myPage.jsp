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
		window.open("http://localhost:8081/SAII/saii/passcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=650px, height=250px, resizable=no, scrollbars=no,top=380px,left=650px");
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
        };
     	// reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0]);
	}else{
		document.getElementById("preview").src="/SAII/Storage/${dto.n_profile_img}";
	}
}
</script>
<style>
#show2{
	display: none;
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
	border: 0px;
	height: 30px;
	background-color: #FF6600;
	color: white;
	cursor: pointer;
	padding: 6px 18px;
	border-radius: 5px;
	font-size: 15px;
	
}

#profile{
	position: relative;
	top: -320px;
	right: 250px;
}
#filebutton {
	display: none;
}

.input_file_button{
	position:relative;
	top:10px;
	background-color: #FF6600;
	color: white;
	cursor: pointer;
	padding: 6px 25px;
	border-radius: 5px;
	display:inline-block;
	width: 100px
}
#sub{
	display: none;
}
.input_submit{
	position:relative;
	top:30px;
	background-color: #FF6600;
	color: white;
	cursor: pointer;
	padding: 6px 25px;
	border-radius: 5px;
	display:inline-block;
	width: 100px
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
	background-color: white;
}

</style>
</head>
<body>
<%@ include file="./top.jsp" %>
<h3>마이페이지</h3>
<hr>
<div class="div1">
	<div class="div1-1" onclick="divshow1()">내 정보</div>
	<div class="div1-1" onclick="divshow2()">내가 만든 코스</div>
	<div class="div1-1" onclick="divshow3()">내가 찜한 코스</div>
</div>
<hr>
<div id="show1" >
	<form method="get" onsubmit="passCk()">
	<input id="edit" type="submit" value="수정하러가기" >
	<div class="mydiv">
	<label class="mylabel">아이디</label>
	<input class="myinput" type="text" id="userId" name="id" value="${dto.id}" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">비밀번호</label>
	<input class="myinput" type="password" name="pw" value="${dto.pw }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">닉네임</label>
	<input class="myinput" type="text" name="nickname" value="${dto.nickname }"readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">이름</label>
	<input class="myinput" type="text" name="name"	value="${dto.name }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">생년월일</label>
	<input class="myinput" type="Date" name="birthday" value="${dto.birthday }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">성별</label>
	<input class="myinput" type="text" name="sex" value="${dto.sex }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">전화</label>
	<input class="myinput" type="text" name="phone" value="${dto.phone }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">이메일</label>
	<input class="myinput" type="email" name="email" value="${dto.email }" readonly>
	</div>
	<div class="mydiv">
	<label class="mylabel">주소</label>
	<input class="myinput" type="text" name="address" value="${dto.address }"readonly>
	</div>
	
	
	<table align="center" width="300px">
		<tr>
			<td><input id="edit" type="submit" value="수정하러가기" ></td>
		</tr>
	</table> 
	<table id="usertable" width="50%" align="center">
		<tr>
			<td>아&nbsp이&nbsp디 : </td>
			<td><input	class="myinput" type="text" id="userId" name="id" value="${dto.id}" readonly></td>
		</tr>
		</form>
		<tr>
			<td>비밀번호 : </td>
			<td><input	class="myinput" type="password" name="pw" value="${dto.pw }" readonly></td>
		</tr>
		<tr>
			<td>닉&nbsp네&nbsp임 : </td>
			<td><input	class="myinput" type="text" name="nickname" value="${dto.nickname }"readonly></td>
		</tr>
		<tr>
			<td>이&nbsp&nbsp&nbsp&nbsp&nbsp름 : </td>
			<td><input	class="myinput" type="text" name="name"	value="${dto.name }" readonly></td>
		</tr>
		<tr>
			<td>생&nbsp&nbsp&nbsp&nbsp&nbsp일 : </td>
			<td><input	class="myinput" type="Date" name="birthday" value="${dto.birthday }" readonly></td>
		</tr>
		<tr>
			<td>성&nbsp&nbsp&nbsp&nbsp&nbsp별 : </td>
			<td><input	class="myinput" type="text" name="sex" value="${dto.sex }" readonly></td>
		</tr>
		<tr>
			<td>전&nbsp&nbsp&nbsp&nbsp&nbsp화 : </td>
			<td><input	class="myinput" type="text" name="phone" value="${dto.phone }" readonly></td>
		</tr>
		<tr>
			<td>이&nbsp메&nbsp일 : </td>
			<td><input	class="myinput" type="email" name="email" value="${dto.email }" readonly></td>
		</tr>
		<tr>
			<td>주&nbsp&nbsp&nbsp&nbsp&nbsp소 : </td>
			<td><input	class="myinput" type="text" name="address" value="${dto.address }"readonly></td>
		</tr>
	</table>
	<div id="profile" align="center">
	<img id="preview" src="/SAII/Storage/${dto.n_profile_img}" width="120px" height="120px" style="border-radius: 50px">
	<form action="http://localhost:8081/SAII/upload?id=${dto.id }" name="img" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${dto.id}">
		<label class="input_file_button" for="filebutton">파일선택</label>
		<input id="filebutton" type="file" name="o_profile_img" onchange="imgcg(this)" required><br>
		<label class="input_submit" for="sub">프로필 변경</label>
		<input id="sub" type="submit" value="프로필변경">
	</form>
	</div>
</div>
<div id="show2">
	<h2>내가 짠 코스</h2>
	<form action="http://localhost:8081/SAII/mypage?id=${UserId }" method="get">
	<table class="t1" align="center" border="1" width="800px">
		<tr>
			<td align="center">
				<select name="searchType">
					<option value="m_title" <c:if test="${map.searchType == 'm_title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${map.searchType == 'content'}">selected</c:if>>내용</option>
				</select>
				<input type="text" name="searchStr" value="${map.searchStr}" />
				<input type="submit" value="검색" />
			</td>
		</tr>
	</table>
	</form>
	
	<table class="t1" align="center" border="1" width="800px">
	<colgroup>
		<col width="5%">
		<col width="15%">
		<col width="30%">
		<col width="30%">
		<col width="10%">
		<col width="10%">
	</colgroup>
		<tr>
			<td>번호</td>
			<td>지역</td>
			<td>제목</td>
			<td>게시날짜</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
	</table>
	<div style="overflow: scroll" id="mboard">
	<table align="center" border="1" width="800px">
	<colgroup>
		<col width="5%">
		<col width="15%">
		<col width="30%">
		<col width="30%">
		<col width="10%">
		<col width="10%">
	</colgroup>
		<c:choose>
			<c:when test="${not empty boardLists}">
				<c:forEach items="${boardLists}" var="list" varStatus="stat">
					<tr>
						<td>${list.m_id}</td>
						<td>${list.region}</td>
						<td><a href="http://localhost:8081/SAII/view?m_id=${list.m_id}">${list.m_title}</a></td>
						<td>${list.m_postdate}</td>
						<td>${list.visitcount}</td>
						<td>${list.goodcount}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</div>
</div>

<div id="show3">
	<h2>내가 찜한 코스</h2>
	<form action="http://localhost:8081/SAII/mypage?id=${UserId }" method="get">
	<table class="t1" align="center" border="1" width="800px">
		<tr>
			<td align="center">
				<select name="searchType">
					<option value="m_title" <c:if test="${map.searchType == 'm_title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${map.searchType == 'content'}">selected</c:if>>내용</option>
				</select>
				<input type="text" name="searchStr" value="${map.searchStr}" />
				<input type="submit" value="검색" />
			</td>
		</tr>
	</table>
	</form>
	
	<table class="t1" align="center" border="1" width="800px">
	<colgroup>
		<col width="5%">
		<col width="15%">
		<col width="30%">
		<col width="30%">
		<col width="10%">
		<col width="10%">
	</colgroup>
		<tr>
			<td>번호</td>
			<td>지역</td>
			<td>제목</td>
			<td>게시날짜</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
	</table>
	<div style="overflow: scroll" id="mboard">
	<table align="center" border="1" width="800px">
	<colgroup>
		<col width="5%">
		<col width="15%">
		<col width="30%">
		<col width="30%">
		<col width="10%">
		<col width="10%">
	</colgroup>
		<c:choose>
			<c:when test="${not empty boardLists}">
				<c:forEach items="${boardLists}" var="list" varStatus="stat">
					<tr>
						<td>${list.m_id}</td>
						<td>${list.region}</td>
						<td><a href="http://localhost:8081/SAII/view?m_id=${list.m_id}">${list.m_title}</a></td>
						<td>${list.m_postdate}</td>
						<td>${list.visitcount}</td>
						<td>${list.goodcount}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</div>
</div>
<div>
	<form action="http://localhost:8081/SAII/withdraw">
		<input type="submit" id="del">
		<label class="withdraw" for="del">회원탈퇴</label>	
	</form>
</div>
<a href="http://localhost:8081/SAII/home">돌아가기</a>


<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>