<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 게시판</title>
	<style type="text/css">
		#title{
			height : 16;
			font-family :'돋움';
			font-size : 12;
			text-align :center;
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
<%@ include file="./top.jsp" %>
	<h2>파일 첨부형 게시판 - 글쓰기(Write)</h2>
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="http://localhost:8081/SAII/review_write" onsubmit="return validateForm(this);">
	<input type="hidden" name="nickname" value="${sessionScope.nickname}" />
	<table border="1" width="90%">

		<tr>
			<td>카테고리</td>
			<td>
				<select name="r_category">
					<option value="course">코스</option>
					<option value="place">장소</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="text" name="r_title" style="width:90%;" />
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:90%;height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
				<input type="file" name="o_file" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">작성 완료</button>
				<button type="reset">RESET</button>
				<button type="button" onclick="location.href='http://localhost:8081/SAII/review_list';">
					목록 바로가기
				</button>
			</td>
		</tr>
	</table>
	</form>


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>