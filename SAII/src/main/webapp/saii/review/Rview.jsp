<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰게시판</title>
<style type="text/css">
.container{
	padding: 0 15%;
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
.container .info button:hover{
	background-color: #99dde4;
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
.cmt-table .cmt{
	border: 1px solid #dddddd;
}
.cmt tr:nth-child(2n){
	background-color: none;
}
.cmt tr:nth-child(2n-1){
	background-color: #f7f7f7;
}
.okCmt{
	margin: 0px;
}

</style>


</head>
<body>
<%@ include file="../top.jsp" %>
	<h2>게시판 상세보기</h2>
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
					<c:if test="${dto.r_category eq 'course'}">코스 리뷰 보기</c:if>
					<c:if test="${dto.r_category eq 'place'}">장소 리뷰 보기</c:if>
					</th>						
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 20%;">제목</td>
					<td>${dto.r_title}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td height="100">${dto.content}</td>
				</tr>
		
				<tr>
					<td>첨부파일</td>
					<td><c:if test="${not empty dto.o_file }">
							<a href="http://localhost:8081/SAII/review_download?o_file=${dto.o_file}&n_file=${dto.n_file}&r_id=${dto.r_id}">
								<img src="/SAII/Storage/${dto.n_file}" width="30%" height="30%"></a>
						</c:if></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<!-- 회원이면, 작성자와 회원 닉네임이 같을 때만, 버튼 보임. -->

					</td>
				</tr>
			</tbody>
		</table>
		<div class="view-btn">
			<c:if test="${!(empty sessionScope.UserId)}">
			<c:if test="${dto.nickname == nickname}">
				<button class="chkWriter" type="button"
					onclick="location.href='http://localhost:8081/SAII/review_mode?mode=edit&r_id=${param.r_id}';">
					수정</button>
				<button class="chkWriter" type="button"
					onclick="location.href='http://localhost:8081/SAII/review_mode?mode=delete&r_id=${param.r_id}';">
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
					<input type="text" name="cmt_content" required placeholder="댓글을 작성하세요. "/>
				</td>
				<td>
					<input type="submit" value="댓글쓰기" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="board_no" value="${ dto.r_id }" />
	</form>

	<table class="cmt">
		<c:if test="${empty commentLists}">
			<tr>
				<td colspan="2">댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${ commentLists }" var="clist">
			<div class="okCmt">
				<tr>
					<td>
						<span>${ clist.nickname }</span>
					</td>
					<td>
						<div>${ clist.cmt_content }</div>
						<div style="font-size:8px; right:20%;">${ clist.cmt_regdate }</div>
					</td>
					<td>
						<c:if test="${!(empty sessionScope.UserId)}">
							<c:if test="${clist.cmt_id == sessionScope.UserId}">
								<input type="button" value="수정"
									onclick="location.href='http://localhost:8081/SAII/editComment?cmt_no=${ clist.cmt_no }&r_id=${ dto.r_id }';"/>
								<input type="button" value="삭제"
									onclick="location.href='http://localhost:8081/SAII/delComment?cmt_no=${ clist.cmt_no }&r_id=${ dto.r_id }';"/>
							</c:if>
						</c:if>
					</td>
				</tr>
			</div>
		</c:forEach>	
	</table>

</div>


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>