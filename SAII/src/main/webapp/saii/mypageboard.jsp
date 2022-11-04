<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	<tr>
		<td>번호</td>
		<td>지역</td>
		<td>제목</td>
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


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>