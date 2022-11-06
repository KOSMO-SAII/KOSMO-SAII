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
<style type="text/css">
.container{
    padding: 0 15% 3% 15%;
}
.container .info p{
	margin: 0;
}
.table-view{
	width: 100%;
	position:relative;
	text-align: center;
	border: 1px solid #dddddd;
}
.container .info{
	text-align:right;
	padding: 3% 0;
}
.container .info button{
    height: 25px;
    width: 130px;
    background: none;
    border: none;
    color: #fff;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    border-radius: 5px;
    background-color: #98dde3;
    white-space: nowrap;
}
.write-btn{
	height: 40px;
    width: 90px;
    vertical-align: middle;
    background: none;
    border: none;
    color: #fff;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    border-radius: 5px;
    background-color: #98dde3;
}
</style>
</head>
<body>
<%@ include file="../top.jsp" %>
	<h2>파일 첨부형 게시판 - 글쓰기(Write)</h2>
<div class="container">
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="http://localhost:8081/SAII/review_write" onsubmit="return validateForm(this);">
	<input type="hidden" name="nickname" value="${sessionScope.nickname}" />
		<div class="container info">
			<p>작성자: ${sessionScope.nickname}</p>
			<button type="button" onclick="location.href='http://localhost:8081/SAII/review_list';">
				목록 바로가기</button>
	</div>	
	<table class="table-view">
		<thead>
			<tr>
				<th colspan="4" style="background-color: #eeeeee; text-align: center;">
				<c:if test="${dto.r_category eq 'course'}">코스 리뷰 보기</c:if>
				<c:if test="${dto.r_category eq 'place'}">장소 리뷰 보기</c:if>
				</th>						
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="width: 20%;">제목</td>
				<td>
					<input type="text" name="r_title"" />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td height="100">
					<textarea name="content"></textarea>
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
					<button class="write-btn" type="submit">작성 완료</button>
					<button class="write-btn" type="reset">RESET</button>
				</td>
			</tr>
		</tbody>
	</table>		


	</form>
</div>
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>