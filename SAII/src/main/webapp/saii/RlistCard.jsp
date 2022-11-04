<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:8081/SAII/saii/CSS/ReviewBoard.css">
</head>
<body>
<h2>리뷰 게시판</h2>
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
			<p align="center">등록된 게시물이 없습니다.</p>
	</c:when>
<c:otherwise>
	
		<div class="ui_box">
			<ul class="list_wrap">
	<c:forEach items="${boardLists}" var="list" varStatus="stat">
			<a href="http://localhost:8081/SAII/review_view?r_id=${list.r_id}">
				<li class="item">
					<img class="image" src="./saii/img/Doldam.jpg " >
						<div class="cont">
<!--
	 						<p>
								${map.totalCount-(((map.pageNum-1)*map.pageSize)+stat.index)}
							</p>
 -->							
							<p>
								<c:if test="${list.r_category eq 'course'}">코스</c:if>
								<c:if test="${list.r_category eq 'place'}">장소</c:if>
							</p>
							<strong class="title">
								<a href="http://localhost:8081/SAII/review_view?r_id=${list.r_id}">${list.r_id}${list.r_title}</a>
							</strong>
							<img src="./"><p>${list.nickname}</p>
							<p>${list.r_postdate}</p>
							<p>${list.visitcount}</p>
							<c:if test="${not empty list.o_file }">
									<a href="http://localhost:8081/SAII/review_download?o_file=${list.o_file}&n_file=${list.n_file}&r_id=${list.r_id}"></a>
							</c:if>
						</div>
					
				</li>
			</a>
	</c:forEach>
			</ul>
		</div>			
</c:otherwise>
</c:choose>	
</body>
</html>