<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="js/jquery-3.6.1.min.js"></script>
<title>리뷰게시판</title>
</head>
<body>
<%@ include file="./top.jsp" %>

	<h2>목록 보기</h2>
	${totalCount}
	<!-- 검색 -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="categoryType">
						<option value="place"
							<c:if test="${map.categoryType=='place' }">selected</c:if>>장소
						</option>
						<option value="course"
							<c:if test="${map.categoryType=='course' }">selected</c:if>>코스		
						</option>
					</select>
					<select name="searchType">
						<option value="r_title"
							<c:if test="${map.searchType=='r_title' }">selected</c:if>>제목
						</option>
						<option value="content"
							<c:if test="${map.searchType=='content' }">selected</c:if>>내용
						</option>
						<option value="both"
                    		<c:if test="${map.searchType=='both' }">selected</c:if>>제목+내용
                  		</option>
					</select>
					<input type="search" name="searchStr" value="${map.searchStr}"/>
					<input type="submit" value="검색" />
				</td>
			</tr>
		</table>
	</form>
		<!-- 목록 -->
	
	<table border="1" width="90%">
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
<c:choose>
	<c:when test="${empty boardLists }">
		<tr>
			<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${boardLists}" var="list" varStatus="stat">
		<tr align="center">
			<td>
				${map.totalCount-(((map.pageNum-1)*map.pageSize)+stat.index)}
			</td>
			<td>
				<c:if test="${list.r_category eq 'course'}">코스</c:if>
				<c:if test="${list.r_category eq 'place'}">장소</c:if>
			</td>
			<td align="left">
				<a href="http://localhost:8081/SAII/review_view?r_id=${list.r_id}">${list.r_id}${list.r_title}</a>
			</td>
			<td>${list.nickname}</td>
			<td>${list.r_postdate}</td>
			<td>${list.visitcount}</td>
			<td>
			
			<c:if test="${not empty list.o_file }">
				<a href="http://localhost:8081/SAII/review_download?o_file=${list.o_file}&n_file=${list.n_file}&r_id=${list.r_id}">[${list.o_file}]</a>
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>		
	</table>
	<!-- 하단 메뉴(페이징, 글쓰기) -->
	<table border="1" width="90%">
		<tr align="center">
			<td>
				${map.pagingStr}
			</td>
			<td width="100">
				<c:choose>
					<c:when test="${empty sessionScope.UserId }">
 					<button type="button"
							onclick="dowrite()">글쓰기</button>
					</c:when>
					<c:otherwise>
					<button type="button"
							onclick="location.href='http://localhost:8081/SAII/review_write';">글쓰기</button>
					</c:otherwise>
				</c:choose>

			</td>
		</tr>
	</table>
<script type="text/javascript">
function dowrite(){
		if(!confirm('비회원 기능이 아닙니다. \n 로그인하시겠습니까?'))
			return false;
		else{
			location.href="http://localhost:8081/SAII/login";
		}
}
</script>


<!--마우스 커서-->
<style type="text/css">* {cursor: url(https://ani.cursors-4u.net/symbols/sym-9/sym833.ani), url(https://ani.cursors-4u.net/symbols/sym-9/sym833.png), auto !important;}</style>
<a href="https://www.cursors-4u.com/cursor/2018/02/17/fast-beating-heart.html" target="_blank" title="Fast Beating Heart"><img src="https://cur.cursors-4u.net/cursor.png" border="0" alt="Fast Beating Heart" style="position:absolute; top: 0px; right: 0px;" /></a>
</body>
</html>