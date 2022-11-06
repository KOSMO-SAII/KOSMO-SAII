<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이</title>
<style type="text/css">
h3{ 
	font-size: 40px;
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

.view-btn{
	position: absolute;
	right: 17%;
	display: inline-block;
}


.cmt-container{
	padding: 40px 15%;
	position:relative;
	
}
.cmt{
	border: 1px solid #dddddd;
	width: 100%
}
.cmt tr:nth-child(2n){
	background-color: none;
}
.cmt tr:nth-child(2n-1){
	background-color: #f7f7f7;
}
.cmt_input{
    vertical-align: bottom;
    width: 100%;
    height: 40px;
    display: inline-block;
    border: none;
    border-radius: 5px;
    background-color: #F7F7F7;
}
.okCmt{
	margin: auto;
}
.cmt-table{
	width: 100%;
	padding-bottom: 2%;
}
.cmt_btn{
	display: inline-block;
    height: 40px;
    width: 80px;
    background: none;
    border: none;
    color: #fff;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    border-radius: 5px;
    background-color: #98dde3;
}
.cmt .date{
	font-size: 8px;
    display: block;
    width: max-content;
    position: relative;
    left: 85%;
 	border: none;
}
.cmt-edit-del{
	width:6%;
}
.cmteditDiv{
	display: none;
}

</style>


</head>
<body>
<%@ include file="../top.jsp" %>
<h3 align="center">게시판 상세보기</h3>
<div class="container">
	<form method="post" action="http://localhost:8081/SAII/review_view">
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
					<td>${dto.r_title}</td>
				</tr>
				<tr>
					<td class="colored">내용</td>
					<td height="100">${dto.content}</td>
				</tr>
				<tr>
					<td class="colored">첨부파일</td>
					<td><c:if test="${not empty dto.o_file }">
							<a href="http://localhost:8081/SAII/review_download?o_file=${dto.o_file}&n_file=${dto.n_file}&r_id=${dto.r_id}">
								<img src="/SAII/Storage/${dto.n_file}" width="30%" height="30%"></a>
						</c:if></td>
				</tr>
			</tbody>
		</table>
		<div class="view-btn">
			<c:if test="${!(empty sessionScope.UserId)}">
			<c:if test="${dto.nickname == nickname}">
				<button type="button"
					onclick="location.href='http://localhost:8081/SAII/review_edit?r_id=${param.r_id}';">
					수정</button>
				<button type="button"
					onclick="delView()">
					삭제</button>
			</c:if>
			</c:if>
		</div>
	</form>
</div>
	<!-- 댓글 목록 -->
<div class="cmt-container">
	<form method="POST" action="http://localhost:8081/SAII/addComment">
		<table class="cmt-table">
			<tr>
				<td>
					<input class="cmt_input" type="text" name="cmt_content" required placeholder="댓글을 작성하세요. "/>
				</td>
				<td align="right" width="90px">
				<c:choose>
					<c:when test="${empty sessionScope.UserId }">
						<input onclick="dowrite()" class="cmt_btn" type="button" value="댓글쓰기" />
					</c:when>
					<c:otherwise>
						<input class="cmt_btn" type="submit" value="댓글쓰기" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</table>
		<input type="hidden" name="board_no" value="${ dto.r_id }" />
	</form>

	<table class="cmt">
		<c:if test="${empty commentLists}">
			<tr style="width: 100%;">
				<td colspan="2">댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${ commentLists }" var="clist">
				<tr>
					<td style="width: 15%">
						<span>${ clist.nickname }</span>
					</td>
					<td>
						<div>${ clist.cmt_content }</div>
						<form method="post" action="http://localhost:8081/SAII/editComment">
							<div class="cmteditDiv" id="editDiv">
								<input type="hidden" name="cmt_no" value="${cmt_no = clist.cmt_no}">
								<input name="cmt_content" value="${cmt_content = clist.cmt_content}" type="text" placeholder="댓글을 수정하세요."/>
								<input onclick="toggleEditDiv()" value="작성완료" type="submit"/>
							</div>
						</form>
						<div class="cmt date">${ clist.cmt_regdate }</div>
					</td>
					
					<c:if test="${!(empty sessionScope.UserId)}">
						<c:if test="${clist.cmt_id == sessionScope.UserId}">
							<td class="cmt-edit-del">
 								<input onclick="toggleEditDiv()" class="cmt-button" type="button" value="수정"/>
								<input class="cmt-button" type="button" value="삭제"
									onclick="location.href='http://localhost:8081/SAII/delComment?cmt_no=${ clist.cmt_no }&r_id=${ dto.r_id }';" />
							</td>
						</c:if>
					</c:if>
				</tr>
		</c:forEach>	
	</table>
</div>
<script src="https://code.jquery.com/jquery-3.6.1.js"; integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="; crossorigin="anonymous";>

</script>
<script type="text/javascript">
function delView(){
	if(!confirm('삭제 시, 되돌릴 수 없습니다. \n 정말 삭제하시겠습니까?'))
		return false;
	else{
		location.href="http://localhost:8081/SAII/review_delete?&r_id=${param.r_id}";
	}
}
function dowrite(){
    if(!confirm('비회원 기능이 아닙니다. \n 로그인하시겠습니까?'))
       return false;
    else{
       location.href="http://localhost:8081/SAII/login";
    }
}
function toggleEditDiv(){
	const editDiv = document.getElementById('editDiv');
	if(editDiv.style.display !== 'none'){
		editDiv.style.display = 'none';
	}
	else{
		editDiv.style.display = 'block';
	}
}


</script>

<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>