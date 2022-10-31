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
		window.name = "parentForm";            
		nickWin = window.open("http://localhost:8081/SAII/saii/passcheck.jsp?id="+document.getElementById("userId").value, "chkForm", 
				"width=200, height=200, resizable = no, scrollbars = no");
	}
</script>
<style>
@font-face {
    font-family: 'Humanbumsuk';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2210-2@1.0/Humanbumsuk.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

body {
	font-family: font-family: 'Humanbumsuk';
}

tr>td:first-child {
	text-align: right
}

body:first-child>table>input,[type=email],[type=Date],[type=text],[type=password] {
	width: 180px;
	height: 30px;
	border:0;
	font-size: 15px;
	border-radius: 10px;
	background-color: rgb(233, 233, 233);
	vertical-align: middle;
}
h4 {
	text-align: center;
}
#firsttable {
	width: 300px;
	background-color: rgb(245, 245, 245);
	border-radius: 20px
}
#edit {
	position: absolute;
	left: 52%;
	top: 65px;
	border-radius: 3px;
	border: 0px;
	background-color: black;
	color: white;
	height: 30px;
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
}

</style>
</head>
<body>
	<h4>회원정보</h4>
	<hr>
	<form method="get" onsubmit="passCk()">
	<input id="edit" type="submit" value="정보 수정하러가기" ><br> 
	<table id="firsttable" width="50%" align="center">
		<tr>
			<td>아&nbsp이&nbsp디 : </td>
			<td><input	type="text" id="userId" name="id" value="${dto.id}" readonly></td>
		</tr>
		</form>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" name="pw" value="${dto.pw }" readonly></td>
		</tr>
		<tr>
			<td>닉&nbsp네&nbsp임 : </td>
			<td><input type="text" name="nickname" value="${dto.nickname }"readonly></td>
		</tr>
		<tr>
			<td>이&nbsp&nbsp&nbsp&nbsp&nbsp름 : </td>
			<td><input type="text" name="name"	value="${dto.name }" readonly></td>
		</tr>
		<tr>
			<td>생&nbsp&nbsp&nbsp&nbsp&nbsp일 : </td>
			<td><input type="Date" name="birthday" value="${dto.birthday }" readonly></td>
		</tr>
		<tr>
			<td>성&nbsp&nbsp&nbsp&nbsp&nbsp별 : </td>
			<td><input type="text" name="sex" value="${dto.sex }" readonly></td>
		</tr>
		<tr>
			<td>전&nbsp&nbsp&nbsp&nbsp&nbsp화 : </td>
			<td><input type="text" name="phone" value="${dto.phone }" readonly></td>
		</tr>
		<tr>
			<td>이&nbsp메&nbsp일 : </td>
			<td><input type="email" name="email" value="${dto.email }" readonly></td>
		</tr>
		<tr>
			<td>주&nbsp&nbsp&nbsp&nbsp&nbsp소 : </td>
			<td><input type="text" name="address" value="${dto.address }"readonly></td>
		</tr>
	</table>
	<div id="profile" align="center">
	<img src="/SAII/Storage/${dto.n_profile_img}" width="100px" height="100px" style="border-radius: 50px">
	<form action="http://localhost:8081/SAII/upload?id=${dto.id }" name="img" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${dto.id}">
		<label class="input_file_button" for="filebutton">파일선택</label>
		<input id="filebutton" type="file" name="o_profile_img"  required><br>
		<label class="input_submit" for="sub">프로필 변경</label>
		<input id="sub" type="submit" value="프로필변경">
	</form>
	</div>
		
	
		<h2>내 코스</h2>
	<form method="get">
		<table align="center" border="1" width="100%">
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
	
	<table align="center" border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>지역</td>
			<td>제목</td>
			<td>코스명</td>
			<td>게시날짜</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		<c:choose>
			<c:when test="${not empty boardLists}">
				<c:forEach items="${boardLists}" var="list" varStatus="stat">
					<tr>
						<td>${list.m_id}</td>
						<td>${list.region}</td>
						<td><a href="http://localhost:8081/SAII/view?m_id=${list.m_id}">${list.m_title}</a></td>
						<td>${list.course_name}</td>
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
		
		<%-- 페이징 --%>
		<tr>
			<td colspan="8" id="paging">
				<c:url var="action" value="http://localhost:8081/SAII/mainboard" />
				<c:if test="${paging.prev}">
					<a href="${action}?page=${paging.beginPage-1}">prev</a>
					&nbsp;&nbsp;
				</c:if>
				<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
					<c:choose>
						<c:when test="${paging.page==index}">
							${index}
						</c:when>
						<c:otherwise>
							<a href="${action}?page=${index}">${index}</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;
				</c:forEach>
				<c:if test="${paging.next}">
					<a href="${action}?page=${paging.endPage+1}">next</a>
				</c:if>
			</td>
		</tr>
	</table>

	<a href="http://localhost:8081/SAII/home">돌아가기</a>
</body>
</html>