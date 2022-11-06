<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이</title>
<style type="text/css">
h3{ 
	font-size: 30px;
    font-weight: 400;
    line-height: 13px;
    margin-top: 80px;
    text-align: center;
    font-weight: bold;
}
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
.colored {
	background-color: #f7f7f7;
}
.container .info{
	text-align:right;
	padding: 3% 0;
}
.container .info button{
    height: 40px;
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
<body>
<%@ include file="../top.jsp" %>
<h3>리뷰 수정</h3>
<div class="container">
	<form name="writeFrm" method="post"
	enctype="multipart/form-data"
	action="http://localhost:8081/SAII/review_edit" onsubmit="return validateForm(this):">
<input type="hidden" name="r_id" value="${dto.r_id }"/>
<input type="hidden" name="prevO_file" value="${dto.o_file }"/>
<input type="hidden" name="prevN_file" value="${dto.n_file }"/>
		<div class="container info">
			<p>작성자: ${dto.nickname}</p>
			<p>작성일: ${dto.r_postdate}</p>
			<p>조회수: ${dto.visitcount}</p>
			<button type="button" onclick="location.href='http://localhost:8081/SAII/review_list';">
				목록 바로가기</button>
		</div>
		<table class="table-view">
			<thead>
				<tr>
					<th colspan="4" style="background-color: #eeeeee; text-align: center;">
					<c:if test="${dto.r_category eq 'course'}">♥ 코스 리뷰 보기 ♥</c:if>
					<c:if test="${dto.r_category eq 'place'}">♥ 장소 리뷰 보기 ♥</c:if>
					</th>						
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="colored" style="width: 20%;">제목</td>
					<td>
						<input type="text" name="r_title" style="width:90%;" value="${ dto.r_title }" placeholder="제목을 입력하세요." />
					</td>
				</tr>
				<tr>
					<td class="colored">내용</td>
					<td>
						<textarea name="content" style="width:90%;height:100px;">${ dto.content }</textarea>
					</td>
				</tr>
				<tr>
					<td class="colored">첨부파일</td>
					<td>
						<input type="file" name="o_file" placeholder="첨부파일" />
						<img src="/SAII/Storage/${dto.n_file}" width="30%" height="30%">
					</td>
				</tr>
			</tbody>
		</table>		
			<div align="center" style="padding-top:2%;">
				<button class="write-btn" type="submit" onclick="okEdit()">작성 완료</button>
				<button class="write-btn" type="reset">RESET</button>
			</div>
</form>
</div>
<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>