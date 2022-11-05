<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 게시판</title>
<style>
	h2{
		text-align: center;
	}
	table{
		width:90%;
		margin:auto;
		border:1px solid black;
	}
	tr,td{
		margin:auto;
		border:1px solid black;
	}
	#paging{
		text-align:center;
	}
	#writeButton{
		text-align:right;
	}
</style>

<!--마우스 커서-->
<script src="https://code.jquery.com/jquery-3.4.1.js"   
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="   
	crossorigin="anonymous">
</script>
</head>
<body>
	<h2>근처 데이트코스 쉽게 찾자~</h2>
	<form method="get">
		<table align="center">
			<tr>
				<td align="center">
					<select name="searchType">
					<option value="region" <c:if test="${map.searchType == 'region'}">selected</c:if>>지역</option>
						<option value="m_title" <c:if test="${map.searchType == 'm_title'}">selected</c:if>>제목</option>
						<option value="course_id" <c:if test="${map.searchType == 'course_id'}">selected</c:if>>코스명</option>
						<option value="nickname" <c:if test="${map.searchType == 'nickname'}">selected</c:if>>작성자</option>
						<option value="content" <c:if test="${map.searchType == 'content'}">selected</c:if>>내용</option>
					</select>
					<input type="searchStr" name="searchStr" value="${map.searchStr}" />
					<input type="submit" value="검색" />
				</td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<td>번호</td>
			<td>지역</td>
			<td>제목</td>
			<td>코스넘버</td>
			<td>작성자</td>
			<td>게시날짜</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		<c:choose>
			<c:when test="${not empty mainBoardLists}">
				<c:forEach items="${mainBoardLists}" var="list" varStatus="stat">
					<tr>
						<td>${list.m_id}</td>
						<td>${list.region}</td>
						<td><a href="http://localhost:8081/SAII/view?m_id=${list.m_id}">${list.m_title}</a></td>
						<td>${list.course_id}</td>
						<td>${list.nickname}</td>
						<td>${list.m_postdate}</td>
						<td>${list.visitcount}</td>
						<td>${list.goodcount}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8">등록된 게시물이 없습니다</td>
				</tr>
			</c:otherwise>
		</c:choose>
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
		<tr>
			<td colspan="7">
				<c:choose>
					<c:when test="${sessionScope.UserId ne null}">
						<input type="button" value="로그아웃" onclick="location.href='http://localhost:8081/SAII/logout';"/>
					</c:when>
					<c:otherwise>
						<input type="button" value="로그인" onclick="location.href='http://localhost:8081/SAII/login';"/>
					</c:otherwise>
				</c:choose>
				<input type="button" value="돌아가기" onclick="location.href='http://localhost:8081/SAII/course_recommend';"/>
				<input type="button" value="처음화면" onclick="location.href='http://localhost:8081/SAII/home';"/>
			</td>
			<td id="writeButton">
				<c:choose>
					<c:when test="${sessionScope.UserId ne null}">
						<input type="button" value="글쓰기" onclick="location.href='http://localhost:8081/SAII/course_write';" />
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>