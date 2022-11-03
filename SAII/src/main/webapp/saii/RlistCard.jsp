<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/ReviewBoard.css">
</head>
<body>

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

<c:choose>
	<c:when test="${empty boardLists }">
		<tr>
			<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
		</tr>
	</c:when>
	<c:otherwise>
	
		<c:forEach items="${boardLists}" var="list" varStatus="stat">
		<ul class="card-list">
			<li class="card-item">
				
				<!-- <figure class="card-image" style="background-image: url("http://localhost:8081/SAII/src/main/webapp/saii/img/Doldam.jpg");">
					<img src="img/Doldam.jpg" alt="">
				</figure> -->
				
				<table class="card-desc">
					<tr align="center">
						<img src="./saii/img/Doldam.jpg " alt="" >
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
				</table>
			</li>
		</ul>		
		</c:forEach>
	</c:otherwise>
</c:choose>	
</body>
</html>