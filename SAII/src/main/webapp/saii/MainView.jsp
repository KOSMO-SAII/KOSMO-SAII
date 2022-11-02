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
	#content{
		height:500px;
	}
	#heart{
		border:0px solid white;
		background-color:white;
	}
	#delete{
		text-align:right;
	}
	#comment{
		width:90%;
		border:1px solid black;
		margin:auto;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td>글번호 ${dto.m_id}</td>
			<td colspan="5">제목 : ${dto.m_title}</td>
		</tr>
		<tr>
			<td>지역</td> <td>${dto.region}</td>
			<td>코스명</td> <td>${dto.m_title}</td>
			<td>글쓴이</td> <td>${dto.nickname}</td>
		</tr>
		<tr>
			<td>작성일</td> <td>${dto.m_postdate}</td>
			<td>좋아요</td>
			<td>
				<c:if test="${memdto.nickname eq null}">
				<c:choose>
					<c:when test="${dto.good}=false">
						<input id="heart" type="submit" value="🤍" /> <%-- 속이 빈 하트 --%>
					</c:when>
					<c:otherwise>
						<input id="heart" type="submit" value="❤" /> <%-- 속이 찬 하트 --%>
					</c:otherwise>
				</c:choose>
				</c:if>
				${dto.goodcount}
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
	<div id="comment">댓글작성 <input type="text"></div>
</body>
</html>