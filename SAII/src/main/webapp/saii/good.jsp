<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>good</title>
</head>
<body>
	<c:if test="${memdto.nickname ne null}">
		<c:choose>
			<c:when test="${goodWhether eq false}">
				<button type="button" id="heart" onclick="location.href='http://localhost:8081/SAII/good?m_id=${dto.m_id}&mode=${0}'">🤍</button> <%-- 속이 빈 하트 --%>
			</c:when>
			<c:otherwise>
				<button type="button" id="heart" onclick="location.href='http://localhost:8081/SAII/good?m_id=${dto.m_id}&mode=${1}'">❤</button> <%-- 속이 찬 하트 --%>
			</c:otherwise>
		</c:choose>
	</c:if>
	${dto.goodcount}
</body>
</html>