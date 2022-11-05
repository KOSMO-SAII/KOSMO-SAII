<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 게시판</title>
<style>
/* 	#title{
			height : 16;
			font-family :'돋움';
			font-size : 12;
			text-align :center;
		} */
#header {
	float: right;
	display: flex;
	justify-content: flex-end;
	align-items: center;
	text-align: center;
	margin-bottom: 20px;
	text-decoration: blink;
	font-weight: bold;
	color: white;
	width: 700px;
	position: relative;
	right: -150px;
	padding: 0;
}

.container {
	padding: 20px;
	min-height: 270px;
}

h2 {
	height: 45px;
	padding: 0 0 0 20px;
	color: #fff;
	font-size: 14px;
	line-height: 45px;
	background-color: #495164;
}

.button {
	color: #fff;
	background-color: #98dde3;
	border-color: #98dde3;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function validateForm(form) {	//필수 항목 입력 확인
		if(form.r_title.value ==""){
			alert("제목을 입력하세요.");
			form.r_title.focus();
			return false;
		}
		if(form.content.value ==""){
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
	}
</script>
</head>
<body>

<%-- <%@ include file="./top.jsp" %> --%>
	<h2>파일 첨부형 게시판 - 글쓰기(Write)</h2>
	<header id="header">
		<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
			<li><c:if test="${UserId!=null}">
					<a href="http://localhost:8081/SAII/mypage?id=${UserId }"
						class="nav-link px-2 link-dark">MyPage</a>
				</c:if></li>
			<li><a href="http://localhost:8081/SAII/saii/startPage.jsp"
				class="nav-link px-2 link-dark">Home</a></li>
			<li test="${UserId==null}"><a href="<!--링크입력하기 -->"
				class="nav-link px-2 link-dark">FAQs</a></li>
			<li test="${UserId==null}"><a href="<!--링크입력하기 -->"
				class="nav-link px-2 link-dark">About</a></li>
			
		</ul>
	</header>
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="http://localhost:8081/SAII/review_write" onsubmit="return validateForm(this);">
	<input type="hidden" name="nickname" value="${sessionScope.nickname}" />
	<table class="container" border="1" width="90%">

		<tr class="category" >
			<td>카테고리</td>
			<td>
				<select name="r_category">
					<option value="course">코스</option>
					<option value="place">장소</option>
				</select>
			</td>
		</tr>
		<tr class="title">
			<td>제목</td>
			<td>
				<input type="text" name="r_title" style="width:90%;" />
			</td>
		</tr>
		<tr class="contents">
			<td>내용</td>
			<td>
				<textarea name="content" style="width:90%;height:100px;"></textarea>
			</td>
		</tr>
		<tr class="attachment">
			<td>첨부파일</td>
			<td>
				<input  type="file" name="o_file" /><!--css가 안먹는데....?-->
			</td>
    </tr>
	</table>
	
  <div class="btns">
    <button class="button" type="submit">작성 완료</button>
    <button class="button" type="reset">RESET</button>
    <button class="button" type="button" onclick="location.href='http://localhost:8081/SAII/review_list';">목록 바로가기</button>
  </div>	
	</form>
	


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>