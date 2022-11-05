<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	table{
		width:90%;
		margin:auto;
		border:1px solid black;
	}
	tr,td{
		margin:auto;
		border:1px solid black;
	}
	#delete{
		text-align:right;
	}
	#good{
		float: left;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="saii/JS/MainView.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>글번호 <span id="m_id">${dto.m_id}</span></td>
			<td colspan="5">제목 : ${dto.m_title}</td>
		</tr>
		<tr>
			<td>지역</td> <td>${dto.region}</td>
			<td>코스넘버</td> <td>${dto.course_id}</td>
			<td>글쓴이</td> <td><span id="nickname">${dto.nickname}</span></td>
		</tr>
		<tr>
			<td>작성일</td> <td>${dto.m_postdate}</td>
			<td>좋아요</td>
			<td>
				<c:if test="${memdto.nickname ne null}">
					<span id="good">
						<c:choose>
							<c:when test="${goodWhether eq true}">
								<img src="saii/img/yesGood.png" alt="좋아" width="20px" height="20px">
							</c:when>
							<c:otherwise>
								<img src="saii/img/notGood.png" alt="싫어" width="20px" height="20px">
							</c:otherwise>
						</c:choose>
					</span>
				</c:if>
				<span id="goodcount">${dto.goodcount}</span>
			</td>
			<td>조회수</td> <td>${dto.visitcount}</td>
		</tr>
		<tr>
			<td id="delete" colspan="6">
				<c:choose>
					<c:when test="${memdto.nickname eq dto.nickname}">
						<input type="button" value="수정하기" onclick="location.href='http://localhost:8081/SAII/edit?m_id=${dto.m_id}';" />
						<input type="button" value="삭제하기" onclick="location.href='http://localhost:8081/SAII/delete?m_id=${dto.m_id}';" />
					</c:when>
				</c:choose>
				<input type="button" value="목록보기" onclick="location.href='http://localhost:8081/SAII/mainboard';" />
			</td>
		</tr>
	</table>
	<table>
		<c:if test="${memdto.nickname ne null}">
			<tr>
				<form method="POST" action="http://localhost:8081/SAII/addMainComments">
				<input type="hidden" name="m_id" value="${dto.m_id}" />
					<td colspan="5">
						댓글작성 <textarea name="comments" cols="150" rows="3" style="resize: none;"></textarea>
					</td>
					<td>
						<input type="submit" value="댓글쓰기" />
					</td>
				</form>
			</tr>
		</c:if>
		<tr>
			<td>프사</td>
			<td>작성자</td>
			<td colspan="3">내용</td>
			<td>게시날짜</td>
		</tr>
		<c:choose>
			<c:when test="${not empty mainCommentsLists}">
				<c:forEach items="${mainCommentsLists}" var="c_list" varStatus="stat">
					<tr>
						<td>프으사아</td>
						<td>${c_list.nickname}</td>
						<td colspan="3">${c_list.comments}</td>
						<td>${c_list.c_postdate}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" align="center">등록된 댓글이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
<!--마우스 커서-->
<%--마우스 커서--%>
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>